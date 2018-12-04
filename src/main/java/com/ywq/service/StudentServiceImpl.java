package com.ywq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ywq.dao.StudentDAO;
import com.ywq.entity.Student;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDAO studentDAO;

	@Override
	public void addStudent(Student student) {
		studentDAO.addStudent(student);
		
	}

	@Override
	public Student getById(Integer id) {
		
		return studentDAO.getById(id);
	}
	
}
