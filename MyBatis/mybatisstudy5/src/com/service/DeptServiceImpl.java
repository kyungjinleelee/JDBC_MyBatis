package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.dao.DeptDAO;
import com.dto.DeptDTO;

public class DeptServiceImpl implements DeptService {
	
	// SqlSessionFactory sqlSessionFactory = null; ==> error. 인스턴스 변수이기 때문에 static 영역에서 사용하지 못한다. 
	// 따라서 static 처리 해줘야함 
	static SqlSessionFactory sqlSessionFactory = null;
	static{
	String resource = "com/config/Configuration.xml";
	InputStream inputStream=null; // 초기화 안해주면 error -> null로 초기화
	try {
		inputStream = Resources.getResourceAsStream(resource);
	} catch (IOException e) {
		e.printStackTrace();
	} // try-catch 안해서 에러 -> surround try/catch로 해결
	 sqlSessionFactory = 
			new SqlSessionFactoryBuilder().build(inputStream);
	}// end static
	
	// 메서드 만들기 (보기쉽게 아이디값과 일치하도록 메서드명 지정)
	
	@Override
	public List<DeptDTO> findAll() {
		List<DeptDTO> list = null;
		// SqlSession 얻기
		SqlSession session = sqlSessionFactory.openSession();
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
		SqlSession session = sqlSessionFactory.openSession();
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
		SqlSession session = sqlSessionFactory.openSession();
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
		SqlSession session = sqlSessionFactory.openSession();
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
		SqlSession session = sqlSessionFactory.openSession();
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
