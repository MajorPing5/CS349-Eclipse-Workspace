package inventory;

public class Item {
	
	// fields - ID, Name, Quantity, Price
	int id, quantity;
	String name;
	double price;
	
	// constructor - empty constructor
	public Item() {
		
	}
	
	/**
	 * Overloaded constructor to retrieve all specified item information.
	 * @param id
	 * @param name
	 * @param quantity
	 * @param price
	 * @return all Item fields
	 */
	public Item(int id, String name, int quantity, double price) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	// setters and getters
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
		return Math.round(price * Math.pow(10, 2)) / Math.pow(10, 2);
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	// custom methods
	/**
	 * Provides all item details for the given object.
	 * @return The string of all Item fields with commas in between - no spaces
	 */
	public String display() {
		return getID()+","
				+getName()+","
				+getQuantity()+","
				+getPrice();
	}
}
