package inventory;

import java.util.ArrayList;

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
