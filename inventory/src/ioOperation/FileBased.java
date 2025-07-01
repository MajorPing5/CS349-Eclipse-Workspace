package ioOperation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.InventoryDataAccess;
import model.InventoryItem;

public class FileBased implements Repository {
	private String fileName;

	public FileBased(String fileName) {
		this.fileName = fileName;
	}

	// Main interface implementation ================================
	@Override
	public ArrayList<InventoryItem> loadInventory() {
		File file = new File(fileName);
		ArrayList<InventoryItem> items = new ArrayList<>();

		if (!file.exists()) {
			createFile();
		}

		try (Scanner inputFile = new Scanner(file)) {
			while (inputFile.hasNextLine()) {
				String line = inputFile.nextLine().trim();
				// Skips any empty lines out of precaution
				if (line.isEmpty()) {
					continue;
				}

				InventoryItem item = parseLine(line);
				items.add(item);
			}

			return items;
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
			return null;
		}

	}

	@Override
	public void saveInventory(InventoryDataAccess inventory) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {

			/**
			 * Due to formatting issues, price is separated from rest of fields to
			 * ensure there are no extra 0s in file writing for retaining proper data integrity.
			 * Using %f%n results in %.6f%n, which obviously can be problematic
			 */

			for (InventoryItem item : inventory.getInventoryList()) {
				writer.printf("%d,%s,%d,",
						item.getID(),
						item.getName(),
						item.getQuantity());
				writer.println(item.getPrice());
			}
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}

	/**
	 * Creates a given file name.
	 *
	 * <p>Precon: filename does not exist;
	 *
	 * <p>Postcon: inventory.txt exists with 4 entries, the last entry being an empty string
	 *
	 * @param filename The string name of the file to check: inventory.txt
	 */
	private void createFile() {
		try (PrintWriter outputFile = new PrintWriter(fileName)) {
			outputFile.println("1,Apple,50,0.5");
			outputFile.println("2,Banana,30,0.3");
			outputFile.println("3,Orange,20,0.7");
		} catch (IOException e) {
			System.err.println("Error creating file: " + e.getMessage());
		}
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