package app;

import java.awt.EventQueue;
import java.util.ArrayList;

import model.*;
import view.*;

public class Main {
	public static void main(String[] args) {
		
		/**
		 * Launch the application.
		 */
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TeacherView teacherFrame = new TeacherView();
						AuthView initialFrame = new AuthView();
						
						teacherFrame.setVisible(false);
						initialFrame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		ArrayList<Course> courses = new CourseDA().getCourseList();
		System.out.println(courses);
	}
}