package exam03_review_insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// 역할: oracle 연동 ==> 조회, 저장, 수정, 삭제 기능들 ...
public class DeptReviewDAO {
	public int insert(Connection con,
						DeptReviewDTO dto
					) throws Exception {
		String sql = "insert into dept (deptno, dname, loc)"
					+ " values(?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, dto.getDeptno());
		pstmt.setString(2, dto.getDname());
		pstmt.setString(3, dto.getLoc());
		
		int n = pstmt.executeUpdate();
		
		pstmt.close();
		
		return n;
	} // end insert
	
	// DB 연동 
	public List<DeptReviewDTO> select(Connection con) throws Exception{
		// while문 안에서 저장한 DTO를 누적용
		List<DeptReviewDTO> list = new ArrayList<>();
		
	  String sql ="select deptno, dname, loc from dept";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  ResultSet rs = pstmt.executeQuery();
	  
	  while(rs.next()){
	      int deptno = rs.getInt("deptno");
	      String dname = rs.getString(2);
	      String loc = rs.getString("loc");
	      DeptReviewDTO dto =
	    		  new DeptReviewDTO(deptno, dname, loc);
	      list.add(dto);
	  }
	  
	  rs.close();
	  pstmt.close();
	  return list;
	  
	} // end select 
	
	
	public DeptReviewDTO selectAll() {
		//DB 접속해서 데이터 가져왔다.
		int deptno = 10;
		String dname = "개발";
		String loc = "서울";
		//System.out.println(deptno+"\t"+dname+"\t"+loc);
		DeptReviewDTO dto = 
				new DeptReviewDTO(deptno, dname, loc);
		return dto;
	}
	
	
	public void update() {}
	public void delete() {}

}
