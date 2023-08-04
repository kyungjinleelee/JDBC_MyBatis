package com.dto;


// dept 테이블의 하나의 행(레코드)을 저장하는 용도의 클래스 -> 4행이면 4개 생성
public class DeptDTO {
	// 컬럼 명으로 된 변수를 가짐
	int deptno; // dept테이블의 deptno 컬럼 저장
	String dname; // dept테이블의 dname 컬럼 저장
	String loc; // dept테이블의 loc 컬럼 저장
	
	public DeptDTO() {} // 변수에 해당하는 생성자 만들기

	public DeptDTO(int deptno, String dname, String loc) {
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}
	
	// getter setter 메서드 만들기 
	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	// toString 메서드 만들기
	@Override
	public String toString() {
		return  deptno+"\t"+dname+"\t"+loc;
	}
	
	
	
}
