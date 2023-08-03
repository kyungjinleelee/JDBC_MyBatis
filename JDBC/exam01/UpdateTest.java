package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTest {

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
			String sql = "update dept set dname=?, loc=? where deptno = ?";
						
			pstmt = con.prepareStatement(sql);
			// ? 대신에 값 설정하기 
			/*
			 * 		pstmt.setXXX(?의위치, 값)
			 * 
			 */
			pstmt.setInt(3, 12); // deptno 값은 중복되지 않도록 확인할 것.
			pstmt.setString(1, "개발부");
			pstmt.setString(2, "서울시");
			
			int num = pstmt.executeUpdate();
			System.out.println("레코드 수정 갯수:" + num);
			
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
			
			// 결과값: 레코드 수정 갯수:0
			// 에러가 난다면, 대부분이 문자열에서 나는 오타! 메서드에서 나는 오타는 컴파일 에러가 남
			
		}

	}

}
