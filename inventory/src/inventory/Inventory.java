package inventory;

import java.util.ArrayList;
import inventory.Item;

public class Inventory {
	private int itemID;
	private ArrayList<Item> items = new ArrayList<>();
	
	public Inventory() {}
	
	public int getItemID() {
		return itemID;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	// custom methods

	/**
	 * Provides the next sequential ID, assuming the last element to be removed was not list.size() - 1.
	 * @return the next highest ID + 1
	 */
	public int getNextItemID() {
		// Base case: List is empty, return ID = 1
		if (getItems().isEmpty()) {
			return 1;
		} else {
			// Retrieve the ID from Index 0 in the current list
			int nextID = getItems().get(0).getID();
			
			for (Item item : getItems()) {
				if (item.getID() > nextID) {
					nextID = item.getID();
				}
			}
			
			return nextID + 1;
		}
	}

	/**
	 * Method to display all items.
	 */
	public void displayInventory() {
		StringBuilder inventoryBlock = inventoryString();

		System.out.println("Current Inventory:\n" + inventoryBlock.toString());
		// JOptionPane.showMessageDialog(null, itemsToDisplay);
	}
	
	public StringBuilder inventoryString() {
		StringBuilder itemsToDisplay = new StringBuilder("");
		
		for(Item items : getItems()) {
			itemsToDisplay.append(items.display()+"\n");
		}
		
		return itemsToDisplay;
	}

	/**
	 * Method to add a new inventory item to the inventory collection.
	 * @param item
	 */
	public void addItem(Item item) {
		getItems().add(item);
	}

	/**
	 * Lambda Method to remove the whole entry if the current ID matches the current element's item ID in array list `Items`.
	 * @param item Object reference of class Item
	 */
	public void removeItem(Item item) {
		// Removes an item where its ID matches the currently element's item ID in Array List Items
		getItems().removeIf(current -> current.getID() == item.getID());
	}

	/**
	 * Updates an item based on the provided Item ID, new name, new quantity, and new price.
	 * @param oldItem Array List of original Item object with specified ID
	 * @param newName String 
	 * @param newQuantity
	 * @param newPrice
	 */
	public void updateItem(Item oldItem, String newName, int newQuantity, double newPrice) {
		// 1. Provided the old item information, find the index in the ArrayList of items
		int i = getItems().indexOf(oldItem);

		// 2. Assuming there's a match, then change based on the following If criteria:
		//		a. If field is not null or the default initialized value, use updated value in items.set(i, Item(id, name, quantity, price)) with appropriate field present
		//		b. If field IS null or the default initialized value, use oldItem value present in the appropriate field.
		String finalName = (newName == null || newName.trim().isEmpty())
				? oldItem.getName() : newName;

		int finalQuantity = (newQuantity == 0)
				? oldItem.getQuantity() : newQuantity;

		double finalPrice = (newPrice  == 0)
				? oldItem.getPrice() : newPrice;

		Item updatedItem = new Item(
				oldItem.getID(),
				finalName,
				finalQuantity,
				finalPrice
				);

		//	3. Replaces existing item at index i with updated information
		getItems().set(i, updatedItem);
	}
}
