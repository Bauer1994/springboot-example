package com.ywq.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ywq.entity.Student;
import com.ywq.mapper.StudentMapper;

@Repository
public class StudentDAOImpl implements StudentDAO{

	@Autowired
	private StudentMapper studentMapper;
	@Override
	public void addStudent(Student student) {
	
		studentMapper.insertSelective(student);
		
	}

	@Override
	public Student getById(Integer id) {
		
		return studentMapper.selectByPrimaryKey(id);
	}

}
