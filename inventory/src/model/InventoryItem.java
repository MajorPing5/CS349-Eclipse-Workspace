package model;

public class InventoryItem {
	// fields - ID, Name, Quantity, Price
	int id, quantity;
	String name;
	double price;

	// constructor - empty constructor
	public InventoryItem() {
	}

	/**
	 * Overloaded constructor to retrieve all specified item information
	 * @param id Integer value of an Item's ID
	 * @param name String name of the Item
	 * @param quantity Integer value of an Item's supply count
	 * @param price double value of an Item's cost towards consumer
	 * {@return int id, String name, int quantity, double price}
	 */
	public InventoryItem(int id, String name, int quantity, double price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * Overloaded constructor to retrieve item detail information
	 * @param name String name of the Item
	 * @param quantity Integer value of an Item's supply count
	 * @param price double value of an Item's cost towards consumer
	 * {@return String name, int quantity, double price}
	 */
	public InventoryItem(String name, int quantity, double price) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	// getters and setters

	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * View specific method that only converts float price into string w/ only 2 decimals
	 * @return the price
	 */
	public String getDisplayPrice() {
		return String.format("%.2f",  price);
	}
}