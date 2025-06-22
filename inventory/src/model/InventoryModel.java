package model;

import java.util.ArrayList;

public class InventoryModel {
	private int itemID;
	private ArrayList<InventoryItem> items = new ArrayList<>();
	
	public InventoryModel() {}
	
	public int getItemID() {
		return itemID;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public ArrayList<InventoryItem> getItems() {
		return items;
	}
	
	public void setItems(ArrayList<InventoryItem> items) {
		this.items = items;
	}
	
	// Custom methods
	/**
	 * Provides the next sequential ID, assuming the last element to be removed was not list.size() - 1.
	 * @return the highest ID + 1
	 */
	public int getNextID() {
		// Base case: List is empty, return ID = 1
		if (getItems().isEmpty()) {
			return 1;
		} else {
			// Retrieve the ID from Index 0 in the current list
			int nextID = getItems().get(0).getID();

			for (InventoryItem item: getItems()) {
				if (item.getID() > nextID) {
					nextID = item.getID();
				}
			}
			return nextID + 1;
		}
	}
	
	/**
	 * Method to add a new inventory item to the inventory collection
	 * @param item object of class InventoryItem that exists in the array list
	 */
	public void addItem(InventoryItem item) {
		items.add(item);
	}
	
	/**
	 * Lambda Method to remove the whole entry if the current ID matches the current element's item ID in array list `Items`.
	 * @param item Object reference of class Item
	 */
	public void removeItem(InventoryItem item) {
		// Removes an item where its ID matches the currently element's item ID in Array List Items
		items.removeIf(current -> current.getID() == item.getID());
	}
	
	/**
	 * Updates an item based on the provided Item ID, new name, new quantity, and new price.
	 * @param itemID int
	 * @param newName String 
	 * @param newQuantity int
	 * @param newPrice float
	 */
	public void updateItem(int itemID, String newName, int newQuantity, float newPrice) {
		// Searches by item ID
		for (int i = 0; i < items.size(); i++) {
			InventoryItem currentItem = items.get(i);
			if (currentItem.getID() == itemID) {
				// Overwrites existing field information with updated information
					currentItem.setName(newName);
					currentItem.setQuantity(newQuantity);
					currentItem.setPrice(newPrice);
					return;
			}
		}
	}
}