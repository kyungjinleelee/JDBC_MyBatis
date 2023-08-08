package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.MyBatisDAO;
import com.dto.StudentDTO;

public class MyBatisServiceImpl implements MyBatisService{
	@Override
	public List<StudentDTO> selectAllStudent(){
		SqlSession session = MySqlSessionFactory.getSession();
		
		List<StudentDTO> list = null;
		try {
			// DAO 연동
			MyBatisDAO dao = new MyBatisDAO();
			list = dao.selectAllStudent(session);
			
		}finally {
			session.close();
		}
		
		return list;
		
	}
}
