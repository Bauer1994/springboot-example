package com.ywq.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_student")
public class Student  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	@Column(name ="name")
	private String name;
	@Column(name ="age")
	private Integer age;
	@Column(name ="addressId")
	private Integer addressId;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Student(Integer id, String name, Integer age, Integer addressId) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.addressId = addressId;
	}

	public Student() {
	}

	@Override
	public String toString() {
		return "student [id=" + id + ", name=" + name + ", age=" + age + ", addressId=" + addressId + "]";
	}
	
	
}
