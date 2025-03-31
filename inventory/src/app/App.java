package app;

import inventory.Inventory;
import inventory.Item;
import java.io.*;
import java.util.Scanner;

// JOPtionPane is not allowed to be used, according to requirements
// import javax.swing.JOptionPane;

public class App {
	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		// Field - filename
		String filename = "inventory.txt";

		/** 
		 * Creates a fresh inventory for the whole program to use.
		 * If the file already exists, it will use this inventory object
		 * to read into it.
		 * If the file does NOT exist, it will simply create the inventory
		 * and proceed with the rest of the program 
		 */
		Inventory inventory = retrieveFile(filename);

		int choiceNo = 0;
		while (choiceNo != 5)
		{ 
			choiceNo = readInt("Welcome to the inventory management system!\n\n"
					+ "Please choose any of the options you wish to run:\n"
					+ "1. Add a new item\n"
					+ "2. View your current inventory\n"
					+ "3. Update an existing item\n"
					+ "4. Delete an existing item\n"
					+ "5. Quit\n\n");
			switch(choiceNo) {
			case 1:
				addItem(inventory);
				break;
			case 2:
				inventory.displayInventory();
				break;
			case 3:
				updateInventory(inventory);
				break;
			case 4:
				removeItem(inventory);
				break;
			case 5:
				fileExport(inventory);
				System.exit(0);
			default:

			}
		}


	}

	/**
	 * Reads an integer from user input with validation
	 * @param prompt String message to prompt user for input
	 * @param allowEmpty Whether to accept empty input (returns 0)
	 * @return Valid integer value (≥ 0)
	 */
	public static int readInt(String prompt) {
	    while (true) {
	        System.out.println(prompt);
	        String entry = keyboard.nextLine().trim();
	        
	        // Handle empty input
	        if (entry.isEmpty()) {
	            return 0;
	        }
	        
	        try {
	            int value = Integer.parseInt(entry);
	            if (value <= 0) {
	                System.out.println("Value cannot be negative. Please enter a number ≥ 0");
	            } else {
	                return value;
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid integer format. Please enter a whole number ≥ 0");
	        }
	    }
	}

	/**
	 * Reads a double from user input with validation
	 * @param prompt String message to prompt user for input
	 * @param allowEmpty Whether to accept empty input (returns 0.0f)
	 * @return Valid float value (≥ 0.0)
	 */
	public static double readDouble(String prompt) {
	    while (true) {
	        System.out.println(prompt);
	        String entry = keyboard.nextLine().trim();
	        
	        // Handle empty input
	        if (entry.isEmpty()) {
	            return 0;
	        }
	        
	        try {
	            double value = Double.parseDouble(entry);
	            if (value <= 0) {
	                System.out.println("Value cannot be negative. Please enter a number ≥ 0");
	            } else {
	                return value;
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid number format. Please enter a numeric value ≥ 0");
	        }
	    }
	}

	/**
	 * Determines if "inventory.txt" should or should not be created.
	 * @param filename The string name of the file
	 * @return boolean
	 */
	private static boolean fileExists(String filename) {
		File file = new File(filename);
		return file.exists();
	}

	/**
	 * Attempts to retrieve the file, and either creates a blank inventory
	 * or reads in file to populate newly created inventory.
	 * @param filename
	 * @return inventory Object
	 * @throws IOException
	 */
	private static Inventory retrieveFile(String filename) throws IOException {
		Inventory inventory = new Inventory();

		if(!fileExists(filename)) {
			PrintWriter outputFile = new PrintWriter(filename);
			outputFile.println("1,Apple,50,0.5");
			outputFile.println("2,Banana,30,0.3");
			outputFile.print("3,Orange,20,0.7");
			outputFile.close();
			retrieveFile(filename);	
		}

		File file = new File(filename);
		Scanner inputFile = new Scanner(file);

		while (inputFile.hasNext()) {
			// Reads the line
			String line = inputFile.nextLine();

			// Scan the line for commas, separating it for each comma present
			String[] arguments = line.split(",");

			// Insert each entry to its appropriate segment
			int id = Integer.parseInt(arguments[0].trim());
			String name = arguments[1].trim();
			int quantity = Integer.parseInt(arguments[2].trim());
			float price = Float.parseFloat(arguments[3].trim());

			// Instantiates Item object
			Item item = new Item(id, name, quantity, price);

			// Adds the new Item object to Inventory
			inventory.addItem(item);
		}
		
		inputFile.close();
		inventory.displayInventory();
		
		return inventory;
	}

	/**
	 * Centralized Item Addition Function.
	 * @param inventory
	 */
	private static void addItem(Inventory inventory) {
		// fields - id, name, quantity, price
		int id = inventory.getNextItemID();

		System.out.println("What is the name of the new item to the inventory?");
		String name = keyboard.nextLine(); 

		int quantity = readInt("How many are being added to your inventory?");

		double price = readDouble("What is the starting price of one of these items?");

		Item item = new Item(id, name, quantity, price);

		inventory.addItem(item);
		inventory.displayInventory();
	}
	
	/**
	 * Removes an item from the inventory list
	 * @param inventory The Array List reference to class Inventory
	 */
	private static void removeItem(Inventory inventory) {
		// Searches the existing list for the desired item for removal
		Item itemToRemove = searchInventory(inventory);
		
		// Checks if the search returned any object or not. If so, then proceed with removal
		if(itemToRemove != null) {
			inventory.removeItem(itemToRemove);
			inventory.displayInventory();
		// If not, then notify user
		} else {
			System.out.println("Item ID not found.");
		}
	}
	
	/**
	 * For all inventory updates, requiring the proper ID of the item.
	 * to search before using its proper index if/when found.
	 * @param inventory Referenced Object Name using class Inventory structure
	 */
	private static void updateInventory(Inventory inventory) {
		// 1. Prompt the user for the item ID
		Item oldItem = searchInventory(inventory);
		
		/** 2. Allow user the chance to update the following 3 fields:
		 *		- Name
		 *		- Quantity
		 *		- Price 
		 */
		if (oldItem != null) {
			System.out.println("For the following questions, if you wish not to change the given field then press Enter:");
			System.out.println("Do you want to change the name of the current item?\n");
			String newName = keyboard.nextLine();

			int newQuantity = readInt("Do you want to update the quantity amount?\n");

			double newPrice = readDouble("Do you want to change the current price of the item?\n");
			
			// 3. Execute updateItem given itemID, name, quantity, and price information
			inventory.updateItem(oldItem, newName, newQuantity, newPrice);
			inventory.displayInventory();
		} else {
			System.out.println("Item ID not found.");
		}
	}
	
	/**
	 * Asks the user for the inventory ID before returning the full item information.
	 * @param inventory
	 * @return foundItem
	 */
	private static Item searchInventory(Inventory inventory) {
		Item foundItem = null;
		
		System.out.println("What's the ID of the item of interest?");
		String inventoryID = keyboard.nextLine();

		for(Item item: inventory.getItems()) {
			if(item.getID() == Integer.parseInt(inventoryID)) {
				foundItem = item;
				break;
			}
		}
		return foundItem;
	}
	
	private static void fileExport(Inventory inventory) {
		try (PrintWriter writer = new PrintWriter(new FileWriter("inventory.txt", false))) {
		writer.println(inventory.inventoryString());
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}
}

