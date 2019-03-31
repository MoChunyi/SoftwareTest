package indi.mcy.ST.lab2;

public class Student {
	private String student_id;
	private String student_name;
	private String student_git;
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public void setStudent_git(String student_git) {
		this.student_git = student_git;
	}
	public String getStudent_id() {
		return student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public String getStudent_git() {
		return student_git;
	}
	Student() {
		
	}
	Student(String id, String name, String git) {
		student_id = id;
		student_name = name;
		student_git = git;
	}
}
