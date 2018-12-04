package com.ywq.service;

import com.ywq.entity.Student;

/**
 * Service层，对Model层的数据进行处理
 * @author YWQ
 *
 */
public interface StudentService {

	void addStudent(Student student);
	
	Student getById(Integer id);
}
