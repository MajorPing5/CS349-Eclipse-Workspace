package model;

import java.util.ArrayList;

import my_util.*;

public class DBO {
	/**
	 * Executes SQL Query for INSERT in Table {@code tb_course}
	 * @param course
	 * @return Boolean for success/failure 
	 */
	public boolean addCourse(Entities course) {
		String query = "INSERT INTO tb_course (code, name, description, max_capacity, status) VALUES (?, ?, ?, ?, 'active')";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, course.getCode());
					parameters.setString(2, course.getCourseName());
					parameters.setString(3, course.getDescription());
					parameters.setInt(4, course.getMaxCapacity());
				},
				null
				);
	}

	/**
	 * Executes SQL Query for INSERT in Table {@code tb_user} for student users explicitly
	 * @param course
	 * @return Boolean for success/failure 
	 */
	public boolean addStudent(Entities student) {
		String query = "INSERT INTO tb_user (first_name, last_name, email, password, role_type, department) VALUES (?, ?, ?, ?, 'student', NULL)";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, student.getFirstName());
					parameters.setString(2, student.getLastName());
					parameters.setString(3, student.getEmail());
					parameters.setString(4, student.getPassword());
				},
				null
				);
	}

	/**
	 * Executes SQL Query for INSERT in Table {@code tb_user} for teacher users explicitly
	 * @param teacher
	 * @return Boolean for success/failure 
	 */
	public boolean addTeacher(Entities teacher) {
		String query = "INSERT INTO tb_user (first_name, last_name, email, password, role_type, department) VALUES (?, ?, ?, ?, 'teacher', ?)";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, teacher.getFirstName());
					parameters.setString(2, teacher.getLastName());
					parameters.setString(3, teacher.getEmail());
					parameters.setString(4, teacher.getPassword());
					parameters.setString(5, teacher.getDepartment());
				},
				null
				);
	}

	/**
	 * Executes SQL Query for INSERT in Table {@code tb_teacher_courses}
	 * @param assignment
	 * @return Boolean for success/failure
	 */
	public boolean addTeacherAssignment(Entities assignment) {
		String query = "INSERT INTO tb_teacher_courses (teacher_id, course_code) VALUES (?, ?)";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setInt(1, assignment.getID());
					parameters.setString(2, assignment.getCode());
				},
				null
				);
	}

	/**
	 * Executes SQL Query for INSERT in Table {@code tb_enrollment}
	 * @param enrollment
	 * @return Boolean for success/failure
	 */
	public boolean addStudentEnrollment(Entities enrollment) {
		String query = "INSERT INTO tb_enrollment (student_id, course_code) VALUES (?, ?)";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setInt(1,  enrollment.getID());
					parameters.setString(2, enrollment.getCode());
				},
				null
				);
	}

	/**
	 * Executes SQL Query for DELETE in Table {@code tb_course}
	 * @param course
	 * @return Boolean for success/failure 
	 */
	public boolean deleteCourse(Entities course) {
		String query = "DELETE FROM tb_course WHERE id=?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, course.getCourseName());
				},
				null
				);
	}

	/**
	 * Executes SQL Query for DELETE in Table {@code tb_enrollment}
	 * @param enrollment
	 * @return Boolean for success/failure 
	 */
	public boolean deleteEnrollment(Entities enrollment) {
		String query = "DELETE FROM tb_enrollment WHERE id=?";

		return new Database().executeQuery(
				query, 
				parameters -> {
					parameters.setInt(1, enrollment.getID());
				}, 
				null);
	}

	/**
	 * Executes SQL Query for DELETE in Table {@code tb_user}, for both Students and Teachers
	 * @param user The user entry
	 * @return Boolean for success/failure 
	 */
	public boolean deleteUser(Entities user) {
		String query = "DELETE FROM tb_user WHERE id=?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setInt(1, user.getID());
				},
				null
				);
	}	

	/**
	 * Executes SQL Query for UPDATE in Table {@code tb_course}
	 * @param course Full object that exists in class Entities for a proper course
	 * @return Boolean for success/failure 
	 */
	public boolean updateCourse(Entities course) {
		String query = "UPDATE tb_course SET name = ?, description = ?, max_capacity = ?, status = ? WHERE id=?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, course.getCourseName());
					parameters.setString(2, course.getDescription());
					parameters.setInt(3, course.getMaxCapacity());
					parameters.setString(4, course.getStatus());
				},
				null
				);
	}

	/**
	 * Executes SQL Query for UPDATE in Table {@code tb_teacher_courses"
	 * @param assignment
	 * @return Boolean for success/failure
	 */
	public boolean updateAssignment(Entities assignment) {
		String query = "UPDATE tb_teacher_courses SET teacher_id = ?, course_code = ? WHERE id=?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setInt(1, assignment.gettID());
					parameters.setString(2, assignment.getCode());
					parameters.setInt(3, assignment.getID());
				},
				null
				);
	}

	/**
	 * Executes SQL Query for UPDATE in Table {@code tb_course}
	 * @param course Full object that exists in class Entities for any user (student or teacher)
	 * @return Boolean for success/failure 
	 */
	public boolean updateUser(Entities user) {
		String query = "UPDATE tb_user SET first_name = ?, last_name = ?, email = ?, password = ?, department = ?,"
				+ " WHERE id=?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, user.getFirstName());
					parameters.setString(2, user.getLastName());
					parameters.setString(3, user.getEmail());
					parameters.setString(4, user.getPassword());
					parameters.setString(5, user.getDepartment());
					parameters.setInt(6, user.getID());
				},
				null
				);
	}

	/**
	 * Executes SQL Query SELECT for tuple ID in Table {@code tb_teacher_courses}
	 * @param tID the Teacher's ID, auto-created from Table {@code tb_user}
	 * @param code the Course Code provided from Table {@code tb_course}
	 * @return int of assignment ID for corresponding (tID, code) pair match
	 */
	public int getAssignmentID(int tID, String code) {
		String query = "SELECT id FROM tb_teacher_courses WHERE teacher_id = ? AND course_code = ?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setInt(1, tID);
					parameters.setString(2, code);
				},
				results -> {
					if(results.next()) {
						return results.getInt("id");
					} else {
						return -1;
					}
				}
				);
	}

	/**
	 * Executes SQL Query SELECT for course code in Table {@code tb_teacher_courses}
	 * @param ID the Teacher's ID, auto-created from Table {@code tb_user}
	 * @return ArrayList of type Entities with assignments tied to matching ID
	 */
	public ArrayList<Entities> getAssignments(int ID) {
		String query = "SELECT course_code FROM tb_teacher_courses WHERE teacher_id = ?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setInt(1, ID);
				},
				results -> {
					ArrayList<Entities> assignments = new ArrayList<>();

					while (results.next()) {
						String code = results.getString("course_code");

						Entities course = new Entities(ID, code);
						assignments.add(course);
					}
					return assignments;
				});
	}

	/**
	 * Executes SQL Query for SELECT from Table {@code tb_course}
	 * 
	 * <p> Query results will consist (in order) of the course's code, name, description, 
	 * teacher assigned's first name, teacher assigned's last name, course max capacity, 
	 * and enrollment status.
	 * 
	 * <p> Note: Teacher's assigned first and last name is pulled from 
	 * {@code tb_user} through {@code tb_teacher_courses}
	 * @return Boolean for success/failure 
	 */
	public ArrayList<Entities> getCourseList() {
		String query = "SELECT c.code AS course_code, "
				+ "c.name, "
				+ "c.description, "
				+ "u.first_name, "
				+ "u.last_name, "
				+ "c.max_capacity, "
				+ "c.status "
				+ "FROM tb_course AS c "
				+ "LEFT JOIN tb_teacher_courses AS inter ON c.code = inter.course_code "
				+ "LEFT JOIN tb_user AS u ON inter.teacher_id = u.id";

		return new Database().executeQuery(
				query,
				null,
				results -> {
					ArrayList<Entities> courseList = new ArrayList<>();

					while (results.next()) {
						String code = results.getString("course_code");
						String name = results.getString("name");
						String desc = results.getString("description");
						String fName = results.getString("first_name");
						String lName = results.getString("last_name");
						int cap = results.getInt("max_capacity");
						String status = results.getString("status");

						Entities course = new Entities(code, name, desc, fName, lName, cap, status);
						courseList.add(course);
					}
					return courseList;
				}
				);
	}

	/**
	 * Executes SQL Query for SELECT from Table {@code tb_user} for any user role = "student"
	 * 
	 * <p> Query results will consist (in order) of the user's:
	 * ID, first name, last name, email, and hashed password
	 * @return ArrayList of students or empty ArrayList
	 */
	public ArrayList<Entities> getStudentList() {
		String query = "SELECT * FROM tb_user WHERE role_type='student'";

		return new Database().executeQuery(
				query,
				null,
				results -> {
					ArrayList<Entities> studentList = new ArrayList<>();

					while (results.next()) {
						int id = results.getInt("id");
						String fName = results.getString("first_name");
						String lName = results.getString("last_name");
						String email = results.getString("email");
						String password = results.getString("password");

						Entities student = new Entities(id, fName, lName, email, password);
						studentList.add(student);
					}
					return studentList;
				}
				);
	}

	public Entities getUserInfoByID(int ID) {
		String query = "SELECT * FROM tb_user WHERE id = ?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setInt(1, ID);
				},
				results -> {
					int id = results.getInt("id");
					String fName = results.getString("first_name");
					String lName = results.getString("last_name");
					String email = results.getString("email");
					String password = results.getString("password");
					String dept = results.getString("department");

					Entities userInfo = new Entities(id, fName, lName, email, password, dept);
					return userInfo;
				}
				);
	}

	public Entities getUserInfoByEmail(String emailTxt) {
		String query = "SELECT * FROM tb_user WHERE email = ?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, emailTxt);
				},
				results -> {
					if (results.next()) {
						int id = results.getInt("id");
						String fName = results.getString("first_name");
						String lName = results.getString("last_name");
						String email = results.getString("email");
						String password = results.getString("password");
						String role = results.getString("role_type");
						String dept = results.getString("department");

						Entities userInfo = new Entities(id, fName, lName, email, password, role, dept);
						return userInfo;
					} else {
					return null;
					}
				}
				);
	}

	/**
	 * Executes SQL Query for SELECT from Table {@code tb_user} for any user role = "teacher"
	 * 
	 * <p> Query results will consist (in order) of the user's:
	 * ID, first name, last name, email, hashed password, and department
	 * @return ArrayList of teachers or empty ArrayList
	 */
	public ArrayList<Entities> getTeacherList() {
		String query = "SELECT * FROM tb_user WHERE role_type='teacher'";

		return new Database().executeQuery(
				query,
				null,
				results -> {
					ArrayList<Entities> teacherList = new ArrayList<>();

					while (results.next()) {
						int id = results.getInt("id");
						String fName = results.getString("first_name");
						String lName = results.getString("last_name");
						String email = results.getString("email");
						String password = results.getString("password");
						String dep = results.getString("department");

						Entities teacher = new Entities(id, fName, lName, email, password, dep);
						teacherList.add(teacher);
					}
					return teacherList;
				}
				);
	}

	public String retrieveRole(String email, String password) {
		String query = "SELECT role_type FROM tb_user WHERE email=? AND password=?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, email);
					parameters.setString(2, Security.hashPassword(password));
				},
				results -> {
					if (results.next()) {
						return results.getString("role_type");
					} else {
						return null;
					}
				});
	}

	public int retriveID(String email) {
		String query = "SELECT id FROM tb_user WHERE email = ?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, email);
				}, results -> {
					if (results.next()) {
						return results.getInt("id");
					} else {
						return -1;
					}
				});
	}

	public Boolean validateEmail(String email) {
		String query = "SELECT email FROM tb_user WHERE email=?";

		return new Database().executeQuery(
				query,
				null,
				results -> {
					if (results.next()) {
						String storedEmail = results.getString("email");
						String inputEmail = email;
						return storedEmail.equals(inputEmail);
					}
					return false;
				}
				);
	}

	public Boolean validatePassword(String email, String password) {
		String query = "SELECT password FROM tb_user WHERE email =?";

		return new Database().executeQuery(
				query,
				parameters -> {
					parameters.setString(1, email);
				},
				results -> {
					if (results.next()) {
						String storedHash = results.getString("password");
						String inputHash = Security.hashPassword(password);
						return storedHash.equals(inputHash);
					}
					return false;
				}
				);
	}
}
