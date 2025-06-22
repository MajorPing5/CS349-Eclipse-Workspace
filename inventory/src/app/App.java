package app;

import view.*;
import model.*;
import controller.*;
import ioOperation.*;

public class App {

	public static void main(String[] args) {
		Repository repository = new FileBased("inventory.txt");
		InventoryView view = new InventoryView();
		InventoryModel model = new InventoryModel();
		CtrlerInventory ctrler = new CtrlerInventory(view, model, repository);
		
		view.setCloseHandler(() -> ctrler.saveInventory());
		view.setVisible(true);
	}
}