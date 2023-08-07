package com.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {

	// SqlSessionFactory sqlSessionFactory = null; ==> error. 인스턴스 변수이기 때문에 static 영역에서 사용하지 못한다. 
		// 따라서 static 처리 해줘야함 
		static SqlSessionFactory sqlSessionFactory = null;
		static{
		String resource = "com/config/Configuration.xml";
		InputStream inputStream=null; // 초기화 안해주면 error -> null로 초기화
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch 안해서 에러 -> surround try/catch로 해결
		 sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		}// end static
		
		// ServiceImpl에서 필요한 SqlSession 리턴하는 메서드 만들기
		// ServiceImpl에서 new 없이 사용하기 위해 static 지정
		public static SqlSession getSession() { // (메서드 이름 (getSession)은 아무거나 해도 괜찮음 
			SqlSession session = sqlSessionFactory.openSession();
		return session;
		}
}
