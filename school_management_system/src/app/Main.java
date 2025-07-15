package app;

import controller.CtrlrAuth;
import model.AuthModel;
import view.AuthView;

public class Main {	
	public static void main(String[] args) {
		AuthView view = new AuthView();
		AuthModel model = new AuthModel();
		new CtrlrAuth(view, model);
		view.setVisible(true);
	}
}