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
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	// custom methods
	/**
	 * 
	 * 
	 */
	public int getNextItemID() {
		// Assuming the list is empty...
		if (items.isEmpty()) {
			return 1;
		}
		
		int nextID = items.get(0).getID();
		
		for (Item item: items) {
			if (item.getID() > nextID) {
				nextID = item.getID();
			}
		}
		return nextID;
	}
	
	/* *Method to add a new inventory item to the inventory collection
	 * @param item
	 */
	public void addItem(Item item) {
		getItems().add(item);
	}
	
	/**
	 * 
	 */
	public void removeItem(Item item) {
		// Get all items that exist
		ArrayList<Item> currentItems = getItems();
		
		// loop until either item is found or End of Inventory
		if (item.getID() == Integer.parseInt(itemID)) {
			
		}
	}
	
	/**
	 * Method to display all items
	 */
	public void displayInventory() {
		
		StringBuilder itemsToDisplay = new StringBuilder("");
		
		ArrayList<Item> items = new ArrayList<>();
		for(Item item: getItems()) {
			itemsToDisplay.append(item.display()+"\n");
		}
		
		// JOptionPane.showMessageDialog(null, itemsToDisplay);
	}
}
