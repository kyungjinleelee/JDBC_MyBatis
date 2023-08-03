package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dao.DeptDAO;
import com.dto.DeptDTO;
import com.exception.DuplicatedDeptnoException;


public class DeptServiceImpl implements DeptService {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "SCOTT";
	String passwd = "TIGER";

	public DeptServiceImpl() { // 생성자 만들고 드라이버로딩한 것 넣어주기
		try {
			Class.forName(driver); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // ctrl + shift + f 들여쓰기 단축키
	}
		// 필요한 메서드 만들기 - select 기능하는 메서드
		// Connection 까지만 얻는다. 나머지 작업은 DAO에서 처리 
		
		@Override
		public List<DeptDTO> findAll(){
			
			List<DeptDTO> list = null;
			Connection con = null;
			
			try {
				con = DriverManager.getConnection(url, userid, passwd); // Connection DB 연결
				// DAO 접근
				DeptDAO dao = new DeptDAO();
				list = dao.findAll(con);
				}
			catch(SQLException e) {
				e.printStackTrace();
			}finally { // DB 연결 해제
				try {
					
					if(con != null) 
						con.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
		}

			return list;
	}
		// insert 기능하는 메서드
	    // Connection 까지만 얻는다. 나머지 작업은 DAO에서 처리
		@Override
		public int insert(DeptDTO dto) throws DuplicatedDeptnoException {
			int n = 0;
			Connection con = null;
			try {
				con = DriverManager.getConnection(url, userid, passwd);
		        // DAO 연동
				DeptDAO dao = new DeptDAO();
				n = dao.insert(con, dto);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return n;
		}

		// update 기능하는 메서드
	    // Connection 까지만 얻는다. 나머지 작업은 DAO에서 처리
		@Override
		public int update(DeptDTO dto) {
			int n = 0;
			Connection con = null;
			try {
				con = DriverManager.getConnection(url, userid, passwd);
				// DAO 연동
				DeptDAO dao = new DeptDAO();
				n = dao.update(con, dto);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return n;
		}
}
