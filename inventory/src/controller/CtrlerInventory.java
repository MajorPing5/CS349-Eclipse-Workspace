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

import exception.Persistence;
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
		} catch (Persistence e) {
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
				String  ID = "";
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
				
				switch(state) {
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
				// Locks the fields in an uneditable manner, preventing any potential tampering with the fields before reading occurs
				view.setState(InventoryView.InventoryState.DISABLE);
				boolean stateChange = false;
				InventoryItem searchResult = null;
				
				if (isPhase1()) {
	                // Phase 1: Process ID lookup for Update or Delete
					searchResult = searchInventory();
	                if (searchResult != null) {
	                	setTxtFields.accept(searchResult);
	                	
	                    if (state.charAt(0) == 'U') {
	                        prepareUpdate(searchResult);
	                        view.setState(InventoryView.InventoryState.ID_OFF);
	                        state = "U2"; // Transition to Update phase 2
	                    } else if (state.charAt(0) == 'D') {
	                        prepareDelete(searchResult);
	                        view.setState(InventoryView.InventoryState.DELETE);
	                        state = "D2"; // Transition to Delete phase 2
	                    }
	                }
	            } else {
	                // Handle non-phase1 states (A, U2, D2)
	                switch(state) {
	                    case "A": 
	                        stateChange = addExecution();
	                        break;
	                    case "U2":
	                        stateChange = updateExecution();
	                        break;
	                    case "D2":
	                        stateChange = deleteExecution();
	                        break;
	                }
	                
	                if (stateChange) {
	                	state = "Main";
	                	view.newTable(model.getItems());
	                	view.swapSouthPanel();
	                }
	            }
			}
		});
	}
	
	// Smaller helper methods for above
	/**
	 * Public method combining validation and search
	 * @return Found item or null if invalid/not found
	 */
	public InventoryItem searchInventory() {
	    Integer existingID = validateInt(view.getTxtID().getText(),"ID", false);
	    return existingID != 0 ? findID(existingID) : null;
	}
	
	private void prepareUpdate(InventoryItem item) {
		view.setState(InventoryView.InventoryState.ID_OFF);
	}
	
	private void prepareDelete(InventoryItem item) {
		view.setState(InventoryView.InventoryState.DISABLE);
	}
	
	private final Consumer<InventoryItem> setTxtFields = item -> {
		view.setTxtName(item.getName());
		view.setTxtQuantity(String.valueOf(item.getQuantity()));
		view.setTxtPrice(String.valueOf(item.getPrice()));
	};
	
	private boolean addExecution() {
		// Assigns all fields with validation - if necessary
		List<Supplier<Object>> validators = Arrays.asList(
				() -> view.getTxtID().getText(),
				() -> validateString(view.getTxtName().getText(), "Name", false),
				() -> validateInt(view.getTxtQuantity().getText(), "Quantity", false),
				() -> validateFloat(view.getTxtPrice().getText(), false)
				);
		
		// Note: ID is pre-set from earlier implementation in "Add" button listener
		// Will automatically iterate through the list to search for anything that is null
		List<Object> results = validators.stream()
				.map(Supplier::get)
				.collect(Collectors.toList());		
		
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
		// Gathers all text fields minus ID
		String txtName = view.getTxtName().getText();
		String txtQuantity = view.getTxtQuantity().getText();
		String txtPrice = view.getTxtPrice().getText();
		
		// Validates that all other fields are in proper form
		String updatedName = validateString(txtName, "Name", true);
		int updatedQuantity = validateInt(txtQuantity, "Quantity", true);
		float updatedPrice = validateFloat(txtPrice, true);
		
		
		if (updatedName == null || updatedQuantity == 0 || updatedPrice == 0.0f) {
			return false;
		} else {		
			model.updateItem(searchResult, updatedName, updatedQuantity, updatedPrice);
			return true;
		}
	}
	
	private boolean deleteExecution() {
		// Quickly converts ID text into integer before removing everything
		String txtId = view.getTxtID().getText();
		int id = Integer.parseInt(txtId);
		
		// Method already attempts to remove if the whole item is found in the inventory system
		model.removeItem(findID(id));
		return true;
	}

	/**
	 * Validates Integer input from text field
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
		        if (value <= 0) {
		            view.failedEntry("Integer Domain Violation", List.of(fieldName));
		            return null;
		        
		        } else {
		        	return value;
		        
		        }
		    } catch (NumberFormatException e) {
		        view.failedEntry("Not An Integer", List.of(fieldName));
		        
		        return null;
		    }
	    }
	}

	/**
	 * Near pure search operation - 1 validation for ID not existing;
	 * Precon: ID field has already been verified to be a proper integer
	 * @param ID an integer that may or may not exist within the inventory 
	 * @return Found item or null w/ failedEntry throw
	 */
	private InventoryItem findID(int ID) {
	    for (InventoryItem item : model.getItems()) {
	        if (item.getID() == ID) {
	            return item;
	            
	        }
	    }
	    view.failedEntry("Integer DNE", List.of("ID"));
	    
	    return null;
	}
	
	// For Validating floats
	private Float validateFloat(String input, boolean isUpdate) {
		input = validateString(input, "Price", isUpdate);
		if (input.isBlank()) {
			return null;
	    	
	    } else {
			try {
		        float value = Float.parseFloat(input);
		        if (value > 0) {
		        	return value;
		        } else {
		        	view.failedEntry("Float Domain Violation", List.of("Price"));		        
		        	return null;
		        }
		    } catch (NumberFormatException e) {
		        view.failedEntry("Not A Float", List.of("Price"));
		        
		        return null;
		    }
	    }
	}

	// For Validating Strings
	private String validateString(String input, String fieldName, boolean isUpdate) {
		/**
		 * Complex conditional, basically checking if input is blank OR
		 * the result of an AND comparison between testing an empty, trimmed string
		 * and isUpdate is false
		 */
		if (!input.trim().isBlank()) {
			return input.trim();
	    } else if (isUpdate) {
			return "";
		} else {
			view.failedEntry("Field left Blank", List.of(fieldName));		
			return null;
		}
    }
	
	private boolean isPhase1() {
		 return (state.length() == 2) && (state.charAt(1) == '1');
	}
}
