package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.lang.Integer;

import ioOperation.Repository;
import model.*;
import view.*;

public class CtrlerInventory {

	private InventoryView view;
	private InventoryModel model;
	private Repository repository;
	private String state = "Main";
	private InventoryItem searchResult;

	public CtrlerInventory(InventoryView view, InventoryModel model, Repository repository) {
		this.view = view;
		this.model = model;
		this.repository = repository;

		InventoryModel loadedModel = null;
		try {
			loadedModel = repository.loadInventory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setItems(loadedModel.getItems());

		view.newTable(new ArrayList<>(model.getItems()));

		view.getBtnDelete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Immediately switch everything BUT the ID field OFF for editing
				view.setState(InventoryView.InventoryState.ID_ON);

				// Update the state to be Delete 1 & display the next phase of the inquiry
				state = "D1";
				view.swapSouthPanel();
			}
		});

		// "Update" button listener
		view.getBtnUpdate().addActionListener(new ActionListener() {
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
			public void actionPerformed(ActionEvent e) {
				// Immediately switch everything BUT the ID field ON for editing
				view.setState(InventoryView.InventoryState.ID_OFF);

				// Automatically calculates the next ID before inserting into the ID Field
				String ID = "";
				ID = String.valueOf(model.getNextID());
				view.setTxtID(ID);

				// Update the state to "Add" & show final south panel
				state = "A";
				view.swapSouthPanel();
			}
		});

		// "Back" Button listener
		view.getBtnBack().addActionListener(new ActionListener() {
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
			public void actionPerformed(ActionEvent e) {
				view.clearEditibleFields();
			}
		});

		view.getBtnSubmit().addActionListener(new ActionListener() {
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
							view.failedEntry("Missing State", List.of("state: " + state));
							break;
						}
					} else {
						// 1. Call Failed Entry error
						view.failedEntry("Integer DNE", List.of("ID"));

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
						view.failedEntry("Missing State", List.of("state: " + state));

						// 2. Does not change existing state string

						// 3. Does not change stateChange to true

						// 4. Re-enable ID field for field correction or back to previous scene
						view.setState(InventoryView.InventoryState.ID_OFF);
						break;
					}

					// Conditional scene switch if CRUD operation is a success
					if (stateChange) {
						state = "Main";
						view.newTable(model.getItems());
						view.swapSouthPanel();
					}
				}
			}
		});
	}

	// Simplified helper methods for above

	/**
	 * Public method combining validation and search
	 * 
	 * @return Found item or null if invalid/not found
	 */
	public InventoryItem searchInventory() {
		Integer existingID = validateInt(view.getTxtID().getText(), "ID", false);
		return existingID != 0 ? findID(existingID) : null;
	}

	public void saveInventory() {
		repository.saveInventory(model);
	}

	private boolean isPhase1() {
		return (state.length() == 2) && (state.charAt(1) == '1');
	}

	private final Consumer<InventoryItem> setTxtFields = item -> {
		view.setTxtName(item.getName());
		view.setTxtQuantity(String.valueOf(item.getQuantity()));
		view.setTxtPrice(String.valueOf(item.getPrice()));
	};

	private boolean addExecution() {
		// Assigns all fields with validation - if necessary
		List<Supplier<Object>> fieldValidators = Arrays.asList(() -> Integer.parseInt(view.getTxtID().getText()),
				() -> validateString(view.getTxtName().getText(), "Name", false),
				() -> validateInt(view.getTxtQuantity().getText(), "Quantity", false),
				() -> validateFloat(view.getTxtPrice().getText(), false));

		// Will automatically iterate through the list to search for anything that is
		// null
		List<Object> results = fieldValidators.stream().map(Supplier::get).collect(Collectors.toList());

		// Searches results to see if anything retains null, indicating a failed field
		if (results.stream().anyMatch(Objects::isNull)) {
			return false;

		} else {
			int ID = (Integer) results.get(0);
			String name = (String) results.get(1);
			int quantity = (Integer) results.get(2);
			float price = (Float) results.get(3);

			model.addItem(new InventoryItem(ID, name, quantity, price));
			return true;
		}
	}

	private boolean updateExecution() {
		List<Supplier<Object>> fieldValidators = Arrays.asList(
				() -> validateString(view.getTxtName().getText(), "Name", true),
				() -> validateInt(view.getTxtQuantity().getText(), "Quantity", true),
				() -> validateFloat(view.getTxtPrice().getText(), true));

		List<Object> results = fieldValidators.stream().map(Supplier::get).collect(Collectors.toList());

		String updatedName = (results.get(0) != "") ? (String) results.get(0) : searchResult.getName();

		int updatedQuantity = (results.get(1) != null) ? (Integer) results.get(1) : searchResult.getQuantity();

		float updatedPrice = (results.get(2) != null) ? (Float) results.get(2) : searchResult.getPrice();

		model.updateItem(searchResult.getID(), updatedName, updatedQuantity, updatedPrice);
		return true;
	}

	private boolean deleteExecution() {
		// Quickly converts ID text into integer before removing everything
		String txtId = view.getTxtID().getText();
		int id = Integer.parseInt(txtId);

		// Method already attempts to remove if the whole item is found in the inventory
		// system
		model.removeItem(findID(id));
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
		for (InventoryItem item : model.getItems()) {
			if (item.getID() == ID) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Validates Integer input from text field
	 * 
	 * @return Validated Integer or null if invalid
	 */
	private Integer validateInt(String input, String fieldName, boolean isUpdate) {
		input = validateString(input, fieldName, isUpdate);
		// Terminates due to empty field without repeatedly mentioning field is empty
		if (input.isBlank()) {
			return null;

		} else {
			try {
				int value = Integer.parseInt(input);
				if (value > 0) {
					return value;
				} else {
					// Silently converts if we're in the update state
					if (!isUpdate) {
						view.failedEntry("Integer Domain Violation", List.of(fieldName));
					}
					return null;
				}
			} catch (NumberFormatException e) {
				// Silently converts if we're in the update state
				if (!isUpdate) {
					view.failedEntry("Not An Integer", List.of(fieldName));
				}
				return null;
			}
		}
	}

	/**
	 * Validates Float input from text field
	 * 
	 * @return Validated float or null if invalid
	 */
	private Float validateFloat(String input, boolean isUpdate) {
		input = validateString(input, "Price", isUpdate);
		// Terminates due to empty field without repeatedly mentioning field is empty
		if (input.isBlank()) {
			return null;

		} else {
			try {
				float value = Float.parseFloat(input);
				if (value > 0) {
					return value;
				} else {
					// Silently converts if we're in the update state
					if (!isUpdate) {
						view.failedEntry("Float Domain Violation", List.of("Price"));
					}
					return null;
				}
			} catch (NumberFormatException e) {
				// Silently converts if we're in the update state
				if (!isUpdate) {
					view.failedEntry("Not A Float", List.of("Price"));
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
				view.failedEntry("Field left Blank", List.of(fieldName));
			}
			return "";
		}
	}

}
