package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import model.InventoryDataAccess;
import model.InventoryItem;
import view.InventoryView;

public class CtrlerInventory {

	private InventoryView view;
	private InventoryDataAccess model;
	private String state = "Main";
	private InventoryItem searchResult;
	private ArrayList<String> blankFields;

	private final Consumer<InventoryItem> setTxtFields = item -> {
		view.setTxtName(item.getName());
		view.setTxtQuantity(String.valueOf(item.getQuantity()));
		view.setTxtPrice(String.valueOf(item.getPrice()));
	};

	// Simplified helper methods for above

	public CtrlerInventory(InventoryView view, InventoryDataAccess model) {
		this.view = view;
		this.model = model;

		// Initializes table with pulled data from loaded database
		view.newTable(
				new ArrayList<>(model.getInventoryList()), 
				item -> new Object[]{
						item.getID(),
						item.getName(),
						item.getQuantity(),
						item.getDisplayPrice()
				}
				);

		// "Delete" Button listener
		view.getBtnDelete().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Immediately switch everything BUT the ID field OFF for editing
				view.setState(InventoryView.InventoryState.ID_ON);
				// Update the state to be Delete 1 & display the next phase of the inquiry
				state = "D1";
				view.swapSouthPanel();
			}
		});

		// "Update" Button listener
		view.getBtnUpdate().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Immediately switch everything BUT the ID field OFF for editing
				view.setState(InventoryView.InventoryState.ID_ON);
				// Update the state to be Update 1 & display the next phase of the inquiry
				state = "U1";
				view.swapSouthPanel();
			}
		});

		// "Add" Button listener
		view.getBtnAdd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Immediately switch everything BUT the ID field ON for editing
				view.setState(InventoryView.InventoryState.ID_OFF);

				// Update the state to "Add" & show final south panel
				state = "A";
				view.swapSouthPanel();
			}
		});

		// "Back" Button listener
		view.getBtnBack().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Assumes that the fields picked are already
				if (state != "D2") {
					view.clearEditibleFields();
				} else {
					view.clearDetailFields();
				}
				switch (state) {
					case "U2":
						state = "U1";
						view.setState(InventoryView.InventoryState.ID_ON);
						break;
					case "D2":
						state = "D1";
						view.setState(InventoryView.InventoryState.ID_ON);
						break;
					default:
						state = "Main";
						view.setState(InventoryView.InventoryState.DISABLE);
						view.swapSouthPanel();
						break;
				}
			}
		});

		// "Clear" Button listener
		view.getBtnClear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.clearEditibleFields();
			}
		});

		// "Clear" Button listener
		view.getBtnClear().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.clearEditibleFields();
			}
		});

		// "Submit" Button listener
		view.getBtnSubmit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Locks the fields in an uneditable manner, preventing any potential tampering
				// with the fields before reading occurs
				view.setState(InventoryView.InventoryState.DISABLE);

				// Only relevant for actual operation execution, to show if anything happened
				boolean stateChange = false;

				if (isPhase1()) {

					/**
					 * Phase 1: Process ID lookup for Update or Delete
					 */
					searchResult = searchInventory();

					if (searchResult != null) {
						setTxtFields.accept(searchResult);

						switch (state.charAt(0)) {

						case 'U':
							view.setState(InventoryView.InventoryState.ID_OFF);
							state = "U2"; // Transition to Update phase 2
							break;
						case 'D':
							view.setState(InventoryView.InventoryState.DELETE);
							state = "D2"; // Transition to Delete phase 2
							break;

						/**
						 * Under NO circumstance should this be called during runtime. If it does, I
						 * screwed up in the code in this segment
						 */
						default:
							view.failedEntry("Missing State", state);
							break;
						}
					} else {
						// 1. Call Failed Entry error
						view.failedEntry("Integer DNE", "ID");

						// 2. Does not change existing state string

						// 3. Does not change stateChange to true

						// 4. Re-enable ID field for field correction or back to previous scene
						view.setState(InventoryView.InventoryState.ID_ON);
					}
				} else {

					/**
					 * Operation Phase (A, U2, D2): Properly execute the operation desired
					 */
					switch (state.charAt(0)) {
						case 'A':
							stateChange = addExecution();
							break;					
						case 'U':
							stateChange = updateExecution();
							break;						
						case 'D':
							stateChange = deleteExecution();
							break;

					/**
					 * Under NO circumstance should this be called during runtime. If it does, I
					 * screwed up in the code in this segment
					 */
					default:
						// 1. Call Failed Entry error
						view.failedEntry("Missing State", state);

							// 2. Does not change existing state string

							// 3. Does not change stateChange to true

							// 4. Re-enable ID field for field correction or back to previous scene
							view.setState(InventoryView.InventoryState.ID_OFF);
							break;
					}

					// Conditional scene switch if CRUD operation is a success
					if (stateChange) {
						view.successEntry();
						state = "Main";
						view.newTable(
								model.getInventoryList(),
								item -> new Object[]{
										item.getID(),
										item.getName(),
										item.getQuantity(),
										item.getDisplayPrice()
								}
								);
						view.swapSouthPanel();
					} else {						
						/**
						 * Operation Phase (A or U2): Reopen previously closed fields used in operation
						 */
						switch (state.charAt(0)) {
							case 'A':
								view.setState(InventoryView.InventoryState.ID_OFF);
								break;							
							case 'U':
								view.setState(InventoryView.InventoryState.ID_OFF);
								break;
						}
					}
				}
			}
		});
	}

	// Simplified helper methods for above

	/**
	 * Final operation in Controller side before Model takes over Creation protocol. Has to verify
	 * the new input information from the user (name, quantity, price) before sending it off to
	 * the model for database operation testing.
	 * 
	 * @return {@code true} or {@code false} depending on success/failure of operation
	 */
	private boolean addExecution() {
		String txtName = view.getTxtName().getText();
		String txtQuantity = view.getTxtQuantity().getText();
		String txtPrice = view.getTxtPrice().getText();
		
		// Assigns all fields with validation - if necessary

		ArrayList<Supplier<Object>> fieldValidators = new ArrayList<>(Arrays.asList(
				() -> validateString(txtName, "Name", false),
				() -> validateInt(txtQuantity, "Quantity", false),
				() -> validateDouble(txtPrice, "Price", false)));

		blankFields = new ArrayList<>();
    
		// Will automatically iterate through the list to search for anything that is null
		ArrayList<Object> results = fieldValidators.stream()
				.map(Supplier::get)
				.collect(Collectors.toCollection(ArrayList::new));

		// Searches results to see if anything retains null, indicating a failed field
		if (results.stream().anyMatch(Objects::isNull)) {
			if (!blankFields.isEmpty()) {
				view.failedEntry("Blank", String.join(", ", blankFields));
				blankFields.clear();
			}
			return false;

		} else {
			String name = (String) results.get(0);
			int quantity = (Integer) results.get(1);
			double price = (Double) results.get(2);

			return model.addItem(new InventoryItem(name, quantity, price));
		}
	}

	private boolean deleteExecution() {
		// Quickly converts ID text into integer before removing everything
		String txtId = view.getTxtID().getText();
		int id = Integer.parseInt(txtId);

		// Method already attempts to remove if the whole item is found in the inventory
		// system
		model.deleteItem(findID(id));
		return true;
	}

	/**
	 * Near pure search operation - 1 validation for ID not existing;
	 *
	 * <p>Precon: ID field has already been verified to be a proper integer
	 *
	 * @param ID an integer that may or may not exist within the inventory
	 * @return Found item or null w/ failedEntry throw
	 */
	private InventoryItem findID(int ID) {
		searchResult = model.getItem(ID);
		return (searchResult instanceof InventoryItem) ? searchResult : null;
	}

	private boolean isPhase1() {
		return (state.length() == 2) && (state.charAt(1) == '1');
	}

	/**
	 * Public method combining validation and search
	 *
	 * @return Found item or null if invalid/not found
	 */
	public InventoryItem searchInventory() {
		Integer existingID = validateInt(view.getTxtID().getText(), "ID", false);
		searchResult = findID(existingID);
		return (searchResult instanceof InventoryItem) ? searchResult : null;
	}

	/**
	 * Final operation in Controller side before Model takes over Update protocol. Has to verify
	 * the new input information from the user (name, quantity, price) before sending it off to
	 * the model for database operation testing.
	 * 
	 * @return {@code true} or {@code false} depending on success/failure of operation
	 */
    private boolean updateExecution() {
		String txtName = view.getTxtName().getText();
		String txtQuantity = view.getTxtQuantity().getText();
		String txtPrice = view.getTxtPrice().getText();
        
		ArrayList<Supplier<Object>> fieldValidators = new ArrayList<>(Arrays.asList(
				() -> validateString(txtName, "Name", true),
				() -> validateInt(txtQuantity, "Quantity", true),
				() -> validateDouble(txtPrice, "Price", true)));
		
		

		ArrayList<Object> results = fieldValidators.stream()
				.map(Supplier::get)
				.collect(Collectors.toCollection(ArrayList::new));

		String updatedName = (results.get(0) != null) ? (String) results.get(0) : searchResult.getName();
		int updatedQuantity = (results.get(1) != null) ? (Integer) results.get(1) : searchResult.getQuantity();
		double updatedPrice = (results.get(2) != null) ? (Double) results.get(2) : searchResult.getPrice();

		return model.updateItem(new InventoryItem(searchResult.getID(), updatedName, updatedQuantity, updatedPrice));
	}

	/**
	 * Validates Double input from text field
	 *
	 * @return Validated Double or null if invalid
	 */
	private Double validateDouble(String input, String fieldName, boolean isUpdate) {
		input = validateString(input, "Price", isUpdate);
		// Terminates due to empty field without repeatedly mentioning field is empty
		if (String.valueOf(input) == "null") {
			return null;

		} else {
			try {
				Double value = Double.parseDouble(input);
				if (value >= 0) {
					return value;
				} else {
					// Silently converts if we're in the update state
					if (!isUpdate) {
						view.failedEntry("Double Domain Violation", fieldName);
					}
					return null;
				}
			} catch (NumberFormatException e) {
				// Silently converts if we're in the update state
				if (!isUpdate) {
					view.failedEntry("Not A Double", fieldName);
				}
				return null;
			}
		}
	}

	/**
	 * Validates Integer input from text field
	 *
	 * @return Validated Integer or null if invalid
	 */
	private Integer validateInt(String input, String fieldName, boolean isUpdate) {
		input = validateString(input, fieldName, isUpdate);
		// Terminates due to empty field without repeatedly mentioning field is empty
		if (String.valueOf(input) == "null") {
			return null;
		} else {
			try {
				int value = Integer.parseInt(input);
				if (value > -1) {
					return value;
				} else {
					// Silently converts if we're in the update state
					if (!isUpdate) {
						view.failedEntry("Integer Domain Violation", fieldName);
					}
					return null;
				}
			} catch (NumberFormatException e) {
				// Silently converts if we're in the update state
				if (!isUpdate) {
					view.failedEntry("Not An Integer", fieldName);
				}
				return null;
			}
		}
	}

	/**
	 * Validates String input from text field
	 *
	 * @return Validated String or empty string if invalid
	 */
	private String validateString(String input, String fieldName, boolean isUpdate) {
		/**
		 * Complex conditional, basically checking if input is blank OR the result of an
		 * AND comparison between testing an empty, trimmed string and isUpdate is false
		 */
		if (!input.isBlank()) {
			return input.trim();
		} else {
			if (!isUpdate) {
				blankFields.add(fieldName);
			}
			return null;
		}
	}
}