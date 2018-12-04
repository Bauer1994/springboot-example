package com.ywq.pojo;

import com.ywq.entity.Student;

public class StudentResponse {
	
	private String name;
	
	private Integer age;
	
	
	public StudentResponse() {
	}

	public StudentResponse(Student student) {
		this.name = student.getName();
		this.age = student.getAge();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "StudentResponse [name=" + name + ", age=" + age + "]";
	}

	

}
