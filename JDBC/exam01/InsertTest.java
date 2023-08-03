package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertTest {

	public static void main(String[] args) {
		
		// DB 연동하기
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "SCOTT";
		String passwd = "TIGER";
		
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "insert into dept (deptno, dname, loc)"
						+ " values(?, ?, ?) "; // +로 문자열 연결해서 쓸 때는, values 앞에 공백 꼭 붙여주기
							// ? 는 바인딩 변수로서 나중에 값을 설정한다.
			pstmt = con.prepareStatement(sql);
			// set.xxx 메서드로 sql문의 ?에 값 설정하기 
			/*
			 * 		pstmt.setXXX(?의위치, 값)
			 * 
			 */
			pstmt.setInt(1, 11);
			pstmt.setString(2, "개발");
			pstmt.setString(3, "서울");
			
			int num = pstmt.executeUpdate(); // DML 요청은 executeUpdate 메서드를 사용 
			System.out.println("레코드 생성 갯수:" + num);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				// 역순으로 close
				if(rs != null) pstmt.close();
				if(rs != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// 결과값: 레코드 생성 갯수:1
			
		}

	}

}
