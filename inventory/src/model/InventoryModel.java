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
	
	// custom methods
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
	 * @param oldItem Array List of original Item object with specified ID
	 * @param newName String 
	 * @param newQuantity
	 * @param newPrice
	 */
	public void updateItem(InventoryItem oldItem, String newName, Integer newQuantity, Float newPrice) {
		// 1. Provided the old item information, find the index in the ArrayList of items
		int i = items.indexOf(oldItem);
		
		/**
		 * 2. Assuming there's a match, then change based on the following If criteria:
		 *		a. If field is not null or the default initialized value, use updated value in
		 *			items.set(i, Item(id, name, quantity, price)) with appropriate field present
		 *		b. If field IS null or the default initialized value, use oldItem value present in
		 *			the appropriate field. 
		 */
		String finalName = (newName == null || newName.isBlank())
				? oldItem.getName() : newName;
		
		int finalQuantity = (newQuantity == null)
				? oldItem.getQuantity() : newQuantity;
		
		float finalPrice = (newPrice  == null)
				? oldItem.getPrice() : newPrice;
		
		InventoryItem updatedItem = new InventoryItem(
				oldItem.getID(),
				finalName,
				finalQuantity,
				finalPrice
				);
		
		//	3. Replaces existing item at index i with updated information
		items.set(i, updatedItem);
	}
}