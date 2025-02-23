package inventory;

import java.util.ArrayList;

public class Inventory {
	private int itemID;
	private ArrayList<Item> items = new ArrayList<>();
	
	public Inventory() {}
	
	public int getItemID() {
		return itemID;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
}
