package com.ywq.dao;

import com.ywq.entity.Student;

/**
 * 使用TkMapper进行数据库操作
 * @author YWQ
 *
 */
public interface StudentDAO {

	void addStudent(Student student);
	
	Student getById(Integer id);
}
