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
	
	public CtrlrAuth(AuthView AuthView, DBO DBO) {
		this.view = AuthView;
		this.model = DBO;
		
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
							new CtrlrAdmin(adminView, model);
							adminView.setVisible(true);
							break;
						case "student":
							StudentView studentView = new StudentView();
							new CtrlrStudent(studentView, model);
							studentView.setVisible(true);
							break;
						case "teacher":
							TeacherView teacherView = new TeacherView();
							new CtrlrTeacher(teacherView, model);
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