package app;

import interaction.*;
import inventory.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inventory inventory = createInventory();
		
		System.exit(0);
	}
	
	/**
	 * Creates an inventory to modify
	 */
	private static Inventory createInventory() {
		// creates an inventory
		Inventory inventory = new Inventory();
		
		return inventory;
	}
	
	/**
	 * Centralized Item Addition Function
	 * @param inventory
	 */
	private static void addItem(Inventory inventory) {
		// field - choiceNo
		private int choiceNo;
		
		choiceNo = invokeChoice();
	}
}
