package exam01;

// import
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {

	public static void main(String[] args) {
		
		// DB 연동하기 (connection 얻기)
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "SCOTT";
		String passwd = "TIGER";
		
		try {
			Class.forName(driver); // 드라이버 클래스 로드, 객체 생성 
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd); // Connection DB 연결
			String sql = "select deptno as no, dname, loc from dept";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // select 요청은 executeQuery 메서드 이용 
			
			while(rs.next()) {
				int deptno = rs.getInt("no"); //컬럼값, getInt(1) 가능
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				System.out.println(deptno + "\t"+ dname +"\t" + loc );  
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally { // DB 연결 해제
			try {
				// 역순으로 close
				// 닫아야 할 자원의 값이 null이라면 error 발생하기 때문에 if문으로 조건 걸어줌
				if(rs != null) rs.close();
				if(rs != null) pstmt.close();
				if(rs != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			/* 결과값 
			10	ACCOUNTING	NEW YORK
			20	RESEARCH	DALLAS
			30	SALES	CHICAGO
			40	OPERATIONS	BOSTON
			50	개발	서울
			60	인사	경기
			80	인사	null
			*/
			
		}

	}

}
