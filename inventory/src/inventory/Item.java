package inventory;

public class Item {
	
	// fields - ID, Name, Quantity, Price
	int id, quantity;
	String name;
	float price;
	
	// constructor - empty constructor
	public Item() {
		
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
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	// custom methods
	/**
	 * Provides all item details for the given object
	 * {@return id, name, quantity, price}
	 */
	public String display() {
		
		double price = 0;
		price = Math.round(getPrice() * Math.pow(10, 2)) / Math.pow(10,2);
				
		return getID()+","
				+getName()+","
				+getQuantity()+","
				+price;
	}
}
