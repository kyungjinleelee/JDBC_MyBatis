package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.DeptDTO;

public interface DeptService{ 
	public List<DeptDTO> findAll(); // 인터페이스라서 {}중괄호 금지! 
	public DeptDTO findByDeptno(int deptno);
	
	// dao에서 복붙하고 session 인자값만 빼기 
	public int addDept(DeptDTO dto);
	public int updateDept(HashMap<String, Object> map);
	public int deleteDept(int deptno);
}



