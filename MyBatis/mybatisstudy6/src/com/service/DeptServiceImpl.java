package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.DeptDAO;
import com.dto.DeptDTO;

public class DeptServiceImpl implements DeptService {
	
	// 메서드 만들기 (보기쉽게 아이디값과 일치하도록 메서드명 지정)
	@Override
	public List<DeptDTO> findAll() {
		List<DeptDTO> list = null;
		
		// SqlSession 얻기
		SqlSession session = MySqlSessionFactory.getSession(); // 클래스명(MySqlSessionFactory)으로 접근가능.
		try {
		////////////
	    // 이제 DAO 연동 ///////// (생성 -> 호출)
		DeptDAO dao = new DeptDAO();
		list = dao.findAll(session);
		//////////////////////
		} finally { // catch문 없는 try/finally 문
		session.close(); // 클로즈 까지 서비스에서 해줘야함 
		}
		return list; 
	}
	@Override
	public DeptDTO findByDeptno(int deptno){
		DeptDTO dto = null;
		// SqlSession 얻기
		SqlSession session = MySqlSessionFactory.getSession();
		try {
		/// dao 연동코드 
			DeptDAO dao = new DeptDAO();
			dto = dao.findByDeptno(session, deptno);
		}
		finally { 
		session.close(); 
		}
		return dto; 
}
	@Override
	public int addDept(DeptDTO dto) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			/// dao 연동코드 
				DeptDAO dao = new DeptDAO();
				n = dao.addDept(session, dto);
				session.commit();
			}
			finally { 
			session.close(); 
			}
		return n;
	}
	
	@Override
	public int updateDept(HashMap<String, Object> map) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			/// dao 연동코드 
				DeptDAO dao = new DeptDAO();
				n = dao.updateDept(session, map);
				session.commit();
			}
			finally { 
			session.close(); 
			}
		return n;
	}
	
	@Override
	public int deleteDept(int deptno) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			/// dao 연동코드 
				DeptDAO dao = new DeptDAO();
				n = dao.deleteDept(session, deptno);
				session.commit();
			}
			finally { 
			session.close(); 
			}
		return n;
	}
}
