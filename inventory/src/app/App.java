package app;

import inventory.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// JOPtionPane is not allowed to be used, according to requirements
// import javax.swing.JOptionPane;

public class App {
	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filename = "inventory.txt";
		
		retrieveFile(filename);
		
		final int choiceNo = 0;
		Inventory inventory = createInventory();
		while (choiceNo != 5)
		{
			switch(choiceNo) {
			case 1:
				addItem(inventory);
				break;
			case 2:
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
	
	private static Inventory retrieveFile(String filename) throws IOException {
		if(fileExists(filename)) {
			File file = new File(filename);
			Scanner inputFile = new Scanner(file);
			
			while (inputFile.hasNext()) {
				createInventory();
				String item = inputFile.nextLine();
				
				
			}
			
			return inventory;
		} else {
			return createInventory();	
		}
	}
	
	/**
	 * Helper function dedicated to searching for the file.
	 * This helps determine if "inventory.txt" should or should not be created
	 * @param filename
	 * @return
	 */
	private static boolean fileExists(String filename) {
		File file = new File(filename);
		return file.exists();
	}
	
	/**
	 * Creates an inventory to modify,
	 * assuming inventory.txt does not already exist
	 */
	private static Inventory createInventory() {
		// creates an inventory
		Inventory inventory = new Inventory();		
		return inventory;
	}
	
	/**
	 * Helper function for testing string conversion to integer
	 * @param prompt String message to prompt user for input
	 * @return int or error
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
	 * @return float or error
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
	 * Centralized Item Addition Function
	 * @param inventory
	 */
	private static void addItem(Inventory inventory) {
		// fields - id, name, quantity, price
		
	}
	
	private static void removeItem(Inventory inventory) {
		String inventoryID = keyboard.nextLine();
		
		Item itemToRemove = null;
		
		for(Item item: inventory.getItems()) {
			if(item.getID() == Integer.parseInt(inventoryID)) {
				itemToRemove = item;
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
