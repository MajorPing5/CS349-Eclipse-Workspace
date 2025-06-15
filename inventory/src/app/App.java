package app;

import view.*;
import model.*;
import controller.*;

public class App {

	public static void main(String[] args) {
		InventoryView view = new InventoryView();
		InventoryModel model = new InventoryModel();
		new CtrlerInventory(view, model);
		view.setVisible(true);
	}
}