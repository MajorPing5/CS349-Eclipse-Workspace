package model;

import java.time.*;

public class Entities {
	private String code, courseName, desc, status, fName, lName, email, password, role, dept, subject, message;
	private int maxCap, id, sendID, recipID, sID, tID;
	private LocalDateTime timestamp;
	private LocalDate enrollDate;
	
	/**
	 * For tuples inserted into Table {@code tb_message}
	 * @param sendID	int assigned from Table {@code tb_user}
	 * @param recipID	int assigned from Table {@code tb_user}
	 * @param code	String the Course Code (i.e. CS101, CS201, etc.)
	 * @param subject
	 * @param message
	 */
	public Entities(int sendID, int recipID, String code, String subject, String message) {
		this.sendID = sendID;
		this.recipID = recipID;
		this.code = code;
		this.subject = subject;
		this.message = message;
	}
	
	/**
	 * For tuples retrieved from Table {@code tb_message}
	 * @param sendID	int assigned from Table {@code tb_user}
	 * @param recipID	int assigned from Table {@code tb_user}
	 * @param code	String the Course Code (i.e. CS101, CS201, etc.)
	 * @param subject
	 * @param message
	 * @param timestamp
	 * @param status
	 */
	public Entities(int sendID, int recipID, String code, String subject, String message, LocalDateTime timestamp, String status) {
		this.sendID = sendID;
		this.recipID = recipID;
		this.code = code;
		this.subject = subject;
		this.message = message;
		this.timestamp = timestamp;
		this.status = status;
	}
	
	/**
	 * For tuples inserted into Table {@code tb_teacher_courses}
	 * @param code	String the Course Code (i.e. CS101, CS201, etc.)
	 * @param id	int auto-assigned from Table {@code tb_user}
	 */
	public Entities(int id, String code) {
		this.id = id;
		this.code = code;
	}
	
	/**
	 * For tuples retrieved from Table {@code tb_teacher_courses}
	 * @param id	int auto-assigned from Table {@code tb_teacher_courses}
	 * @param code	String the Course Code (i.e. CS101, CS201, etc.)
	 * @param tid	int auto-assigned from Table {@code tb_user}
	 */
	public Entities(int id, int tID, String code) {
		this.id = id;
		this.tID = tID;
		this.code = code;
	}
	
	/**
	 * For tuples inserted into Table {@code tb_enrollment}
	 * @param sID	int auto-assigned from Table {@code tb_user}
	 * @param code String the Course Code (i.e. CS101, CS201, etc.)
	 * @param enrollDate	date of successful enrollment
	 */
	public Entities(int sID, String code, LocalDate enrollDate) {
		this.sID = sID;
		this.code = code;
		this.enrollDate = enrollDate;
	}
	
	/**
	 * For tuples retrieved from Table {@code tb_enrollment}
	 * @param id	int auto-assigned from Table {@code tb_enrollment}
	 * @param sID	int auto-assigned from Table {@code tb_user}
	 * @param code	String the Course Code (i.e. CS101, CS201, etc.)
	 * @param enrollDate	date of successful enrollment
	 */
	public Entities(int id, int sID, String code, LocalDate enrollDate) {
		this.id = id;
		this.sID = sID;
		this.code = code;
		this.enrollDate = enrollDate;
	}
	
	 /** For tuples retrieved from Table {@code tb_user} for students
	 * @param id
	 * @param fName
	 * @param lName
	 * @param email
	 * @param password
	 */
	public Entities(int id, String fName, String lName, String email, String password) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
	}
	
	/**
	 * For tuples retrieved from Table {@code tb_user} for teachers
	 * @param id
	 * @param first_name
	 * @param last_name
	 * @param email
	 * @param password
	 * @param dept
	 */
	public Entities(int id, String fName, String lName, String email, String password, String dept) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.dept = dept;
	}

	/**
	 * For tuples inserted into Table {@code tb_course}
	 * @param code	String the Course Code (i.e. CS101, CS201, etc.)
	 * @param courseName	String of course's full name
	 * @param desc	String of a full description pertaining to the course in question
	 * @param maxCap	integer of total number of students allowed in a single class
	 * @param status	String of Open or Closed status
	 */
	public Entities(String code, String courseName, String desc, int maxCap, String status) {
		this.code = code;
		this.courseName = courseName;
		this.desc = desc;
		this.maxCap = maxCap;
		this.status = status;
	}
	
	/**
	 * For tuples retrieved from Table {@code tb_course}, automatically displaying instructor assignment from Table {@code tb_teacher_courses}
	 * @param code	String the Course Code (i.e. CS101, CS201, etc.)
	 * @param courseName	String of course's full name
	 * @param desc	String of a full description pertaining to the course in question
	 * @param fName String of teacher's first name
	 * @param lName String of teacher's last name
	 * @param maxCap	integer of total number of students allowed in a single class
	 * @param status	String of Open or Closed status
	 */
	public Entities(String code, String courseName, String desc, String fName, String lName, int maxCap, String status) {
		this.code = code;
		this.courseName = courseName;
		this.desc = desc;
		this.fName = fName;
		this.lName = lName;
		this.maxCap = maxCap;
		this.status = status;
	}

	/**
	 * For tuples associated with Table {@code tb_user}
	 * @param id
	 * @param fName
	 * @param lName
	 * @param email
	 * @param password
	 * @param role_type
	 * @param dept
	 */
	public Entities(int id, String fName, String lName, String email, String password, String role, String dept) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.dept = dept;
	}

	/**
	 * @return the Course Code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return the Course Name
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * @return the Department name
	 */
	public String getDepartment() {
		return dept;
	}
	
	/**
	 * @return the Description of a course
	 */
	public String getDescription() {
		return desc;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return the Enrollment Date
	 */
	public LocalDate getEnrollmentDate() {
		return enrollDate;
	}
	
	/**
	 * @return the First Name of a user
	 */
	public String getFirstName() {
		return fName;
	}
	
	/**
	 * @return the User ID
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * @return the Last Name of a user
	 */
	public String getLastName() {
		return lName;
	}
	
	/**
	 * @return the Max Capacity of a course
	 */
	public int getMaxCapacity() {
		return maxCap;
	}
	
	/**
	 * @return the body of a message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @return the Recipient ID
	 */
	public int getRecipientID() {
		return recipID;
	}
	
	/**
	 * @return the Role Type
	 */
	public String getRoleType() {
		return role;
	}
	
	/**
	 * @return the Sender ID
	 */
	public int getSenderID() {
		return sendID;
	}
	
	/**
	 * @return the ID of a user with role "teacher"
	 */
	public int gettID() {
		return tID;
	}

	/**
	 * @param tID the ID of a user with role "teacher" to set
	 */
	public void setTID(int tID) {
		this.tID = tID;
	}

	/**
	 * @return the status of a course/read status of a message
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @return the Student ID of a user with role "student"
	 */
	public int getStudentID() {
		return sID;
	}
	
	/**
	 * @return the subject line of a message
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * @return the timestamp
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	/**
	 * @param code the Course Code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @param courseName the Course Name to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * @param dept the Department name to set
	 */
	public void setDepartment(String dept) {
		this.dept = dept;
	}
	
	/**
	 * @param desc the Description of a course to set
	 */
	public void setDescription(String desc) {
		this.desc = desc;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @param enrollDate the Enrollment Date to set
	 */
	public void setEnrollmentDate(LocalDate enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	/**
	 * @param fName the First Name of a user to set
	 */
	public void setFirstName(String fName) {
		this.fName = fName;
	}
	
	/**
	 * @param id the User ID to set
	 */
	public void setID(int id) {
		this.id = id;
	}
	
	/**
	 * @param lName the Last Name of a user to set
	 */
	public void setLastName(String lName) {
		this.lName = lName;
	}
	
	/**
	 * @param maxCap the Max Capacity of a course to set
	 */
	public void setMaxCapacity(int maxCap) {
		this.maxCap = maxCap;
	}
	
	/**
	 * @param message the body of a message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @param recipID the Recipient ID to set
	 */
	public void setRecipientID(int recipID) {
		this.recipID = recipID;
	}
	
	/**
	 * @param role the Role Type to set
	 */
	public void setRoleType(String role) {
		this.role = role;
	}
	
	/**
	 * @param sendID the Sender ID to set
	 */
	public void setSenderID(int sendID) {
		this.sendID = sendID;
	}
	
	/**
	 * @param status the read status or open/close of a message or course respectively to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @param sID the Student ID of a user with role "student" to set
	 */
	public void setStudentID(int sID) {
		this.sID = sID;
	}
	
	/**
	 * @param subject the subject line of a message to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
