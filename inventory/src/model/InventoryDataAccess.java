package model;

import java.util.ArrayList;

import ioOperation.DatabaseConnection;

public class InventoryDataAccess {

	/**
	 * Complex Getter/Method that retrieves the full Inventory List
	 * @return ArrayList of InventoryItem objects
	 */
	public ArrayList<InventoryItem> getInventoryList() {

		String query = "SELECT * FROM inventory_items";

		return new DatabaseConnection().executeQuery(
				query,
				null,
				results -> {
					ArrayList<InventoryItem> itemList = new ArrayList<>();

					while (results.next()) {
						int id = results.getInt("id");
						String name = results.getString("name");
						int qty = results.getInt("quantity");
						double price = results.getDouble("price");

						InventoryItem item = new InventoryItem(id, name, qty, price);
						itemList.add(item);
					}
					return itemList;
				});
	}

	/**
	 * Complex Getter/Method that retrieves ONE object of InventoryItem by its id
	 * @param ID
	 * @return
	 */
	public InventoryItem getItem(int ID) {
		String query = "SELECT * FROM inventory_items WHERE id = ?";

		return new DatabaseConnection().executeQuery(
				query,
				parameter -> {
					parameter.setInt(1, ID);
				},
				results -> {
					if (results.next()) {
						int id = results.getInt("id");
						String name = results.getString("name");
						int qty = results.getInt("quantity");
						double price = results.getDouble("price");

						InventoryItem item = new InventoryItem(id, name, qty, price);
						return item;
					} else {
						return null;
					}
				}
				);
	}

	/**
	 * Creates and appends a new item to the item array list
	 * @param item complete element from {@code ArrayList<InventoryItem>}
	 * @return {@code true} or {@code false} depending on success/failure of operation
	 */
	public boolean addItem(InventoryItem item) {

		String query = "INSERT INTO inventory_items (name, quantity, price) VALUES (?, ?, ?)";

		return new DatabaseConnection().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, item.getName());
					parameters.setInt(2, item.getQuantity());
					parameters.setDouble(3, item.getPrice());
				},
				null
				);
	}

	/**
	 * Updates an existing item in the item array list
	 * @param item complete element from {@code ArrayList<InventoryItem>}
	 * @return {@code true} or {@code false} depending on success/failure of operation
	 */
	public boolean updateItem(InventoryItem item) {

		String query = "UPDATE inventory_items SET name = ?, quantity = ?, price = ? WHERE id = ?";

		return new DatabaseConnection().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, item.getName());
					parameters.setInt(2, item.getQuantity());
					parameters.setDouble(3, item.getPrice());
					parameters.setInt(4, item.getID());
				},
				null
				);
	}

	/**
	 * Deletes an existing item from the item array list
	 * @param item complete element from {@code ArrayList<InventoryItem>}
	 * @return {@code true} or {@code false} depending on success/failure of operation
	 */
	public boolean deleteItem(InventoryItem item) {

		String query = "DELETE FROM inventory_items WHERE id=?";

		return new DatabaseConnection().executeQuery(
				query,
				parameters -> {
					parameters.setInt(1, item.getID());
				},
				null
				);

	}
}