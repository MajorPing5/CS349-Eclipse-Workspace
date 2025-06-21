package ioOperation;

import java.io.*;
import java.util.*;

import model.*;

public class FileBased implements Repository {
	private String fileName;
	private InventoryModel inventory;

	public FileBased(String fileName) {
		this.fileName = fileName;
	}

	 // Main interface implementation ================================
	@Override
    public InventoryModel loadInventory() {
        try {
        	inventory = new InventoryModel();
            
            if (!fileExists()) {
                createFile();
            }
            
            File file = new File(fileName);
            
            try (Scanner inputFile = new Scanner(file)) {
                while (inputFile.hasNextLine()) {
                    String line = inputFile.nextLine().trim();
                    if (line.isEmpty()) continue;
                    
                    InventoryItem item = parseLine(line);
                    inventory.addItem(item);
                }
            }
            return inventory;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }

    }
	
    @Override
    public void saveInventory(InventoryModel inventory) {
        fileExport(inventory);
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
	 * <p>Precon: filename does not exist;
	 * <p>Postcon: inventory.txt exists with 4 entries, the last entry being an empty string	
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
     * Initializes entire exporting process
     * @param inventory the {@code InventoryModel} to copy/paste into the fileName
     */
    private void fileExport(InventoryModel inventory) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
            writer.println(inventoryString(inventory));
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    /**
     * Breaks down segments of string to provide into the txt file
     * @param inventory
     * @return all items to display in the written file
     */
    private StringBuilder inventoryString(InventoryModel inventory) {
		StringBuilder itemsToDisplay = new StringBuilder("");
		
		for(InventoryItem items : inventory.getItems()) {
			itemsToDisplay.append(items.display()+"\n");
		}
		
		return itemsToDisplay;
	}
    
    /**
     * Separates each line by the comma, labeling each element in the line appropriately
     * @param line entry provided from {@code StringBuilder}
     * @return
     */
    private InventoryItem parseLine(String line) {
        String[] arguments = line.split(",");
        int id = Integer.parseInt(arguments[0].trim());
        String name = arguments[1].trim();
        int quantity = Integer.parseInt(arguments[2].trim());
        float price = Float.parseFloat(arguments[3].trim());
        
        return new InventoryItem(id, name, quantity, price);
    }
}