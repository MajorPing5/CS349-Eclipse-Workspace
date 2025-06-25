package ioOperation;

import model.InventoryModel;

public interface Repository {
    /**
     * Attempts to retrieve the file, creates a blank inventory if it doesn't exist,
	 * and reads in the file to populate the newly created inventory.
	 * 
	 * <p>Precon:	None
	 * <p>Postcon:	inventory, and object of class Inventory, is now populated with all existing data
     */
	 InventoryModel loadInventory();
	 
	 /**
	 * Prompts the application to begin exporting the data from the software to the inventory.txt file
	 * @throws Persistence
	 */
	 void saveInventory(InventoryModel inventory);
}