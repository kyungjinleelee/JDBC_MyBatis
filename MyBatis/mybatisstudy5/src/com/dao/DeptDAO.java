package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.DeptDTO;

public class DeptDAO { 
	// service에 있는 메서드를 붙임
	public List<DeptDTO> findAll(SqlSession session) {
		// 서비스에서 session을 넘겨받는 작업을 해줘야함 (findAll 파라미터 삽입)
		List<DeptDTO> list = session.selectList("DeptMapper.findAll");
		return list; // 여러개니까 list로 받음
	}
	
	public DeptDTO findByDeptno(SqlSession session, int deptno) {
		DeptDTO dto = 
				session.selectOne("DeptMapper.findByDeptno", deptno);
		return dto;
	}
	
	// DML
	// insert문은 세션, dto 파라미터 필요하다
	public int addDept(SqlSession session, DeptDTO dto) {
		int n = session.insert("DeptMapper.addDept", dto);
		return n;
	}
	
	public int updateDept(SqlSession session, HashMap<String, Object> map) {
		int n = session.insert("DeptMapper.updateDept", map);
		return n;
	}
	
	public int deleteDept(SqlSession session, int deptno) {
		int n = session.insert("DeptMapper.deleteDept", deptno);
		return n;
	}
	
	
}
