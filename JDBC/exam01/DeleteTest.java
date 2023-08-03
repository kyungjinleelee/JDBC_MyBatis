package exam01;

import java.sql.Connection; // java.sql 패키지 임포트
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteTest {

	public static void main(String[] args) {
		
		// DB 연동하기
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // DB와 연결을 위해 URL과 계정 정보가 필요
		String userid = "SCOTT";
		String passwd = "TIGER";
		
		try {
			Class.forName(driver); // 드라이버 로드 (각 DBMS마다 이름은 다름)
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "delete from dept where deptno=?";
						
			pstmt = con.prepareStatement(sql);
			// ? 대신에 값 설정하기 
			/*
			 * 		pstmt.setXXX(?의위치, 값)
			 * 
			 */
			pstmt.setInt(1, 12); // deptno 값은 중복되지 않도록 확인할 것.
			
			int num = pstmt.executeUpdate();
			System.out.println("레코드 삭제 갯수:" + num);
			
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
			
			// 결과값: 레코드 삭제 갯수:1
			// 에러가 난다면, 대부분이 문자열에서 나는 오타! 메서드에서 나는 오타는 컴파일 에러가 남
			
		}

	}

}
