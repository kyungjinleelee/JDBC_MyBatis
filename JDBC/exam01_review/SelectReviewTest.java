package exam01_review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectReviewTest {

	public static void main(String[] args) throws Exception {
		// 1. 4가지 정보 입력
		 String driver = "oracle.jdbc.driver.OracleDriver";
		 String url ="jdbc:oracle:thin:@localhost:1521:xe";
		 String usrid ="SCOTT";
		 String passwd ="TIGER";
		// 2. 드라이버 로딩 
		 Class.forName(driver);
	    // 3. 커넥션 연결
		 Connection con = DriverManager.getConnection(url, usrid, passwd );
		 
		// 4. sql문 작성
		 String sql ="select deptno, dname, loc from dept";
		// 5. sql문 전달 객체 (PreparedStatement)
		 PreparedStatement pstmt = con.prepareStatement(sql);
		 
		// 6. sql문 전달 (select문은 ResultSet으로 )
		 ResultSet rs = pstmt.executeQuery();
		 
		 while(rs.next()){
		      int deptno = rs.getInt("deptno");
		      String dname = rs.getString(2);
		      String loc = rs.getString("loc");
		      System.out.println(deptno+"\t"+dname+"\t"+loc);
		  }
		 // 7. 역순으로 close하기
		 rs.close();
		 pstmt.close();
		 con.close();
		 
	}

}
