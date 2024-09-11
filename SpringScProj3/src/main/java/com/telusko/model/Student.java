package com.telusko.model;

public class Student {

	Integer id;
	String name;
	String courseName;
		
	public Student() {
		
	}
	public Student(Integer id, String name, String courseName) {
		super();
		this.id = id;
		this.name = name;
		this.courseName = courseName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", courseName=" + courseName + "]";
	}
	
	
	
}
