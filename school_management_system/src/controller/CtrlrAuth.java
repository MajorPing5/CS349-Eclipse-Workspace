package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import my_util.Validation;
import view.*;
import view.common.PanelOperations;

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
					Entities userInfo = model.getUserInfoByEmail(view.getEmailField().getText());
					String role = userInfo.getRoleType();
					
					// Clears the current view's frame window
					view.dispose();

					// Loads the appropriate view window based on the pulled user's role
					switch (role) {
					case "admin":
						AdminView adminView = new AdminView();
						new CtrlrAdmin(adminView, model);
						break;
					case "student":
						StudentView studentView = new StudentView();
						new CtrlrStudent(studentView, model, userInfo);
						break;
					case "teacher":
						TeacherView teacherView = new TeacherView(userInfo.getFirstName(), userInfo.getLastName());
						new CtrlrTeacher(teacherView, model, userInfo);
						break;
					}
				} else {
					view.getErrorField().setVisible(true);
				}
			}
		});
	}
}