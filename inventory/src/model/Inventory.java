package model;

import java.util.ArrayList;

public class Inventory {
	private int itemID;
	private ArrayList<ItemModel> itemModels = new ArrayList<>();
	
	public Inventory() {}
	
	public int getItemID() {
		return itemID;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public ArrayList<ItemModel> getItems() {
		return itemModels;
	}
	
	public void setItems(ArrayList<ItemModel> itemModels) {
		this.itemModels = itemModels;
	}
	
	// custom methods
	/**
	 * 
	 * 
	 */
	public int getNextID() {
		// Assuming the list is empty...
		if (itemModels.isEmpty()) {
			return 1;
		}
		
		int nextID = itemModels.get(0).getID();
		
		for (ItemModel itemModel: itemModels) {
			if (itemModel.getID() > nextID) {
				nextID = itemModel.getID();
			}
		}
		return nextID;
	}
	
	/**
	 * Method to add a new inventory item to the inventory collection
	 * @param item
	 */
	public void addItem(ItemModel itemModels) {
		getItems().add(itemModels);
	}
	
	/**
	 * 
	 */
	public void removeItem(ItemModel itemModels) {
		// Get all items that exist &		
		// loop until either item is found or End of Inventory
		for (ItemModel currentItems : itemModels) {
			if (currentItems ==  itemModels) {
				
			}
		}
	}
	
	/**
	 * Method to display all items
	 */
	public void displayInventory() {
		
		StringBuilder itemsToDisplay = new StringBuilder("");
		
		for(ItemModel itemModels : getItems()) {
			itemsToDisplay.append(itemModels.display()+"\n");
		}
		
		// JOptionPane.showMessageDialog(null, itemsToDisplay);
	}
}
