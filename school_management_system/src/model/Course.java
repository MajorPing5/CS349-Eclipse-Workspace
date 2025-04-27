/**
 * 
 */
package model;

/**
 * 
 */
public class Course {
	private String code, course_name, description, status;
	private int max_capacity;
	
	@Override
	public String toString() {
		return "Course [code=" + code + ", course_name=" + course_name + ", description=" + description + ", status="
				+ status + ", max_capacity=" + max_capacity + "]";
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the course_name
	 */
	public String getCourse_name() {
		return course_name;
	}
	/**
	 * @param course_name the course_name to set
	 */
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the max_capacity
	 */
	public int getMax_capacity() {
		return max_capacity;
	}
	/**
	 * @param max_capacity the max_capacity to set
	 */
	public void setMax_capacity(int max_capacity) {
		this.max_capacity = max_capacity;
	}
}
