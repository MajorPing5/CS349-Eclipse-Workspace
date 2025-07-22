package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import my_util.Validation;
import view.*;

public class CtrlrAuth {
	
	private AuthView view;
	private DBO model;
	private Validation valid = new Validation();
	
	public CtrlrAuth(AuthView view, DBO model) {
		this.view = view;
		this.model = model;
		
		view.getBtnLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (valid.login(view.getEmailField(), view.getPasswordField())) {					
					String role = model.retrieveRole(
							view.getEmailField().getText(),
							new String(view.getPasswordField().getPassword()));							
					view.dispose();
					
					switch (role) {
						case "admin":
							AdminView adminView = new AdminView();
							adminView.setVisible(true);
							break;
						case "student":
							StudentView studentView = new StudentView();
							studentView.setVisible(true);
							break;
						case "teacher":
							TeacherView teacherView = new TeacherView();
							teacherView.setVisible(true);
							break;
					}
				} else {
					view.getErrorField().setVisible(true);
				}
			}
		});
	}
}