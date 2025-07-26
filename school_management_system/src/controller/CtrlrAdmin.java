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
		
		// Courses Administration Button
		view.sysManBtns.getBtnCourses().addActionListener(new ActionListener() {
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
		
		// Students Administration Button
		view.sysManBtns.getBtnStudents().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Entities> students = model.getStudentList();				
				view.activateAdminPanel(
						"Students",
						students,
						info -> {
							Entities student = (Entities) info;
							return new Object[] {
									student.getID(),
									student.getFirstName(),
									student.getLastName(),
									student.getEmail(),
									student.getPassword()
							};
						});
			}
		});
		
		// Teachers Administration Button
		view.sysManBtns.getBtnTeachers().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}	
}
