package app;

import controller.CtrlrAuth;
import model.DBO;
import view.AuthView;

public class Main {	
	public static void main(String[] args) {
		AuthView view = new AuthView();
		DBO model = new DBO();
		new CtrlrAuth(view, model);
	}
}