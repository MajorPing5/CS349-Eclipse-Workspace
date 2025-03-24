package app;

import view.*;
import model.*;
import controller.*;

public class Main {

	public static void main(String[] args) {

		ContactView view = new ContactView();
		ContactModel model = new ContactModel();
		new ContactController(view, model);
		view.setVisible(true);
	}

}
