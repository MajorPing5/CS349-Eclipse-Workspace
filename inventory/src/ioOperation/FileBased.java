package ioOperation;

import java.io.*;
import java.util.*;

import exception.Persistence;
import model.*;

public class FileBased implements Repository {
	private String fileName;

	public FileBased(String fileName) {
		this.fileName = fileName;
	}

	 // Main interface implementation ================================
    /**
     * Immediately attempts to search for any trace of inventory.txt in root directory
     * @throws Persistence
     */
	@Override
    public InventoryModel loadInventory() throws Persistence{
        try {
            return retrieveFile();
        } catch (IOException e) {
            throw new Persistence("Failed to load inventory", e);
        }

    }
	
	/**
	 * Prompts the application to begin exporting the data from the software to the inventory.txt file
	 * @throws Persistence
	 */
    @Override
    public void saveInventory(InventoryModel inventory) throws Persistence{
        fileExport(inventory);
    }

    /**
	 * Attempts to retrieve the file, creates a blank inventory if it doesn't exist,
	 * and reads in the file to populate the newly created inventory.
	 * @return inventory Object
	 * @throws IOException
	 */
    private InventoryModel retrieveFile() throws IOException {
    	InventoryModel inventory = new InventoryModel();
        
        if (!fileExists()) {
            createFile();
        }
        
        readFile(inventory);
        return inventory;
    }
    
    /**
     * Determines if "inventory.txt" should or should not be created.
     * @param filename The string name of the file
     * @return boolean
     */
    private boolean fileExists() {
    	File file = new File(fileName);
    	return file.exists();
    }
    
    /**
	 * Creates a given file name.
	 * Precon: filename does not exist;
	 * Postcon: inventory.txt exists with 4 entries, the last entry being an empty string	
	 * @param filename The string name of the file to check: inventory.txt
	 * @throws IOException
	 */
    private void createFile() throws IOException {
        try (PrintWriter outputFile = new PrintWriter(fileName)) {
            outputFile.println("1,Apple,50,0.5");
            outputFile.println("2,Banana,30,0.3");
            outputFile.println("3,Orange,20,0.7");
        }
    }
    
    /**
	 * Reads the provided file and adds it to the inventory object.
	 * Precon: inventory.txt exists;
	 * Postcon: inventory, and object of class Inventory, is now populated with all existing data
	 * @param filename The string name of the file to check: inventory.txt 
	 * @param inventory An object from the class Inventory, containing 4 fields for each entry
	 * @throws IOException
	 */
    private void readFile(InventoryModel inventory) throws IOException {
        File file = new File(fileName);
        
        try (Scanner inputFile = new Scanner(file)) {
            while (inputFile.hasNextLine()) {
                String line = inputFile.nextLine().trim();
                if (line.isEmpty()) continue;
                
                InventoryItem item = parseLine(line);
                inventory.addItem(item);
            }
        }
    }
    
    private void fileExport(InventoryModel inventory) throws Persistence{
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
            for (InventoryItem item : inventory.getItems()) {
                writer.println(serializeItem(item));
            }
        } catch (IOException e) {
            throw new Persistence("Save failed: " + e.getMessage(), e);
        }
    }
    
    // Helper method for processing file
    private InventoryItem parseLine(String line) {
        String[] arguments = line.split(",");
        int id = Integer.parseInt(arguments[0].trim());
        String name = arguments[1].trim();
        int quantity = Integer.parseInt(arguments[2].trim());
        float price = Float.parseFloat(arguments[3].trim());
        
        return new InventoryItem(id, name, quantity, price);
    }
    
    // Helper for saving
    private String serializeItem(InventoryItem item) {
        return String.format("%d,%s,%d,%.2f", 
            item.getID(), 
            item.getName(), 
            item.getQuantity(), 
            item.getPrice());
    }
}