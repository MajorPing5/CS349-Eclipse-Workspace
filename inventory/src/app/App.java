package app;

import model.Inventory;
import model.ItemModel;

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
					+ "5. Quit");
			switch(choiceNo) {
			case 1:
				addItem(inventory);
				break;
			case 2:
				inventory.displayInventory();
				break;
			case 3:
				break;
			case 4:
				removeItem(inventory);
				break;
			case 5:
				System.exit(0);
			default:
				
			}
		}
		
		
	}

	/**
	 * Helper function for testing string conversion to integer
	 * @param prompt String message to prompt user for input
	 * @return int
	 * @throws NumberFormatException
	 */
	public static int readInt(String prompt) {
		while (true) {
			System.out.print(prompt);
			String entry = keyboard.nextLine();
			try {
				return Integer.parseInt(entry);
			} catch (NumberFormatException e) {
				System.out.println("Entry not recognized. Please enter a valid integer");
			}
		}
	}
	
	/**
	 * Helper function for testing string conversion to float
	 * @param prompt String message to prompt user for input
	 * @return float
	 * @throws NumberFormatException
	 */
	public static float readFloat(String prompt) {
		while (true) {
			System.out.print(prompt);
			String entry = keyboard.nextLine();
			try {
				return Float.parseFloat(entry);
			} catch (NumberFormatException e) {
				System.out.println("Entry not recognized. Please enter a valid float value");
			}
		}
	}
	
	/**
	 * Helper function dedicated to searching for the file.
	 * This helps determine if "inventory.txt" should or should not be created
	 * @param filename
	 * @return inventory object
	 */
	private static boolean fileExists(String filename) {
		File file = new File(filename);
		return file.exists();
	}
	
	/**
	 * Attempts to retrieve the file, and either creates a blank inventory
	 * or reads in file to populate newly created inventory
	 * @param filename
	 * @return inventory Object
	 * @throws IOException
	 */
	private static Inventory retrieveFile(String filename) throws IOException {
		Inventory inventory = new Inventory();
		
		if(fileExists(filename)) {
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
				ItemModel itemModel = new ItemModel(id, name, quantity, price);
				
				// Adds the new Item object to Inventory
				inventory.addItem(itemModel);
			}
			
			inputFile.close();
		}
		
		return inventory;
	}
	
	/**
	 * Centralized Item Addition Function
	 * @param inventory
	 */
	private static void addItem(Inventory inventory) {
		// fields - id, name, quantity, price
		int id = inventory.getNextID();

		System.out.print("What is the name of the new item to the inventory?\n");
		String name = keyboard.nextLine(); 
		
		int quantity = readInt("How many is being added to your inventory?");
		
		float price = readFloat("What is the starting price of one of these items?");
		
		ItemModel itemModel = new ItemModel(id, name, quantity, price);
		
		inventory.addItem(itemModel);
	}
	
	private static void removeItem(Inventory inventory) {
		String inventoryID = keyboard.nextLine();
		
		ItemModel itemToRemove = null;
		
		for(ItemModel itemModel: inventory.getItems()) {
			if(itemModel.getID() == Integer.parseInt(inventoryID)) {
				itemToRemove = itemModel;
				break;
			}
		}
		if(itemToRemove != null) {
			inventory.removeItem(itemToRemove);
			inventory.displayInventory();
		} else {
			System.out.print("Item ID not found.");
		}
	}
}
