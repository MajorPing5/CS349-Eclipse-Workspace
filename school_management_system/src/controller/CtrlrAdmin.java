package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.DBO;
import model.Entities;
import my_util.Validation;
import view.AdminView;

public class CtrlrAdmin {
	
	private AdminView view;
	private DBO model;
	private Validation valid = new Validation();

	public CtrlrAdmin(AdminView AdminView, DBO DBO) {
		this.view = AdminView;
		this.model = DBO;
		
		view.sysManageButtons.getBtnCourses().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Entities> courses = model.getCourseList();				
				view.activateAdminPanel(
						"Courses",
						courses,
						item -> {
							Entities course = (Entities) item;
							return new Object[] {
									course.getCode(),
									course.getCourseName(),
									course.getDescription(),
									course.getFirstName(),
									course.getLastName(),
									course.getMaxCapacity(),
									course.getStatus()
							};
						});
			}
		});
	}	
}
