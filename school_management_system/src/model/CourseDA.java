package model;

import java.sql.*;
import java.util.ArrayList;

import my_util.*;

public class CourseDA {
	public boolean addCourse(Course course) {
		String query = "INSERT INTO tb_course (code, name, description, max_capacity, status) VALUES (?, ?, ?, ?, 'active')";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, course.getCode());
					parameters.setString(2, course.getCourse_name());
					parameters.setString(3, course.getDescription());
					parameters.setInt(4, course.getMax_capacity());
				},
				null
				);
	}

	public boolean updateCourse(Course course) {
		String query = "UPDATE tb_course SET name = ?, description = ?, max_capacity = ?, status = ? WHERE id=?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, course.getCourse_name());
					parameters.setString(2, course.getDescription());
					parameters.setInt(3, course.getMax_capacity());
					parameters.setString(4, course.getStatus());
				},
				null
				);
	}
	
	/**
	 * "Remove" method to delete a course from the course ArrayList 
	 * @param courseToRemove
	 */
	public boolean deleteCourse(Course course) {
		String query = "DELETE FROM tb_course WHERE id=?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, course.getCourse_name());
				},
				null
				);
	}

	public ArrayList<Course> getCourseList() {
		String query = "SELECT * FROM tb_course";

		return new Database().executeQuery(
				query,
				null,
				results -> {
					ArrayList<Course> courseList = new ArrayList<>();

					while (results.next()) {
						String code = results.getString("code");
						String name = results.getString("name");
						String desc = results.getString("description");
						int cap = results.getInt("max_capacity");
						String status = results.getString("status");

						Course course = new Course(code, name, desc, status, cap);
						courseList.add(course);
					}
					return courseList;
				}
				);
	}
}
