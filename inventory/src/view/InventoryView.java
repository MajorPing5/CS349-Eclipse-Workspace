package view;

import javax.swing.*;
import java.awt.*;

public class InventoryView extends JFrame {
	// TODO JTable or JList for inventory display
	// TODO JTextField for input
	// TODO JButton components for actions
	// TODO Error & Success Dialog Boxes
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
				+ "5. Quit\n");
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
	 * @param prompt String message for user's input
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
}