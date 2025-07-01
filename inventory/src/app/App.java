package app;

import java.util.ArrayList;

import controller.CtrlerInventory;
import ioOperation.FileBased;
import ioOperation.Repository;
import model.InventoryDataAccess;
import model.InventoryItem;
import view.InventoryView;

public class App {
	private static void dbmsCheck(InventoryDataAccess model) {
		// Check if DBMS is empty - enables automatic importing from "inventory.txt" to DBMS
        ArrayList<InventoryItem> dbInventory = model.getInventoryList();
        if (dbInventory.isEmpty()) {
        	// Initializes the file-based approach IF AND ONLY IF the database is already a clean slate
        	Repository repo = new FileBased("inventory.txt");

            // Load inventory from file system
            ArrayList<InventoryItem> fileInventory = repo.loadInventory();

            // Insert file items into DBMS
            for (InventoryItem item : fileInventory) {
                model.addItem(new InventoryItem(
                    item.getName(),
                    item.getQuantity(),
                    item.getPrice()
                ));
            }
            // For debugging purposes
            System.out.println("Migrated " + fileInventory.size() + " items from file to database");
        }
	}

	public static void main(String[] args) {
		InventoryView view = new InventoryView();
		InventoryDataAccess model = new InventoryDataAccess();
		new CtrlerInventory(view, model);
		dbmsCheck(model);
		view.setVisible(true);
	}
}