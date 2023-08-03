package exam02_review_select;

public class DeptReviewDTO {

	int deptno;
	String dname;
	String loc;
	
	public DeptReviewDTO() {}

	public DeptReviewDTO(int deptno, String dname, String loc) {
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}

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

	@Override
	public String toString() {
		return "DeptReviewDTO [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}
	
	
	
}
