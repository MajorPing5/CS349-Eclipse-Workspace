package controller;

import model.*;
import view.*;

public class CtrlerInventory {
	
	private InventoryView view;
	private InventoryModel model;
	
	public CtrlerInventory(InventoryView view, InventoryModel model) {
		this.view = view;
		this.model = model;
		
		view.newTable();
	}
}