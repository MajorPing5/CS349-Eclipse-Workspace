package app;

import java.awt.EventQueue;
import java.util.ArrayList;

import model.*;
import view.*;

public class Main {
	private static final int WINDOW_WIDTH = 400;   // Window width
	private static final int WINDOW_HEIGHT = 300;  // Window height
	public static void main(String[] args) {
		
		/**
		 * Launch the application.
		 */
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						AuthView initialFrame = new AuthView();
						initialFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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