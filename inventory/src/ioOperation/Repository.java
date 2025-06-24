package ioOperation;

import java.io.*;
import java.util.*;

import model.*;

//InventoryRepository.java
public interface Repository {
	/**
	 * Attempts to retrieve the file, creates a blank inventory if it doesn't exist,
	 * and reads in the file to populate the newly created inventory.
	 *
	 * <p>Precon:	None
	 * <p>Postcon:	inventory, and object of class Inventory, is now populated with all existing data
	 */
	public ArrayList<InventoryItem> loadInventory();

	/**
	 * Prompts the application to begin exporting the data from the software to the inventory.txt file
	 * @throws Persistence
	 */
	public void saveInventory(InventoryDataAccess inventory);
}