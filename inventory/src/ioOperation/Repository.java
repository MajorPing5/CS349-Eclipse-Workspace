package ioOperation;

import model.InventoryModel;
import exception.Persistence;

//InventoryRepository.java
public interface Repository {
	 InventoryModel loadInventory() throws Persistence;
	 void saveInventory(InventoryModel inventory) throws Persistence;
}