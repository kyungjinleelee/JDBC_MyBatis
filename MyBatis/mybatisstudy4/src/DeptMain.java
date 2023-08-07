import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.dto.DeptDTO;

public class DeptMain {

	public static void main(String[] args) throws Exception{

		// Configuration.xml 파일 읽기
		String resource = "com/config/Configuration.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		// Dynamic SQL
		// 1. multi select
		// select * from dept where deptno IN (값, 값2, ...) (dept 갯수 = 정해져있지 않음)
		List<Integer> deptnoList = Arrays.asList(10,20,30,40);
	
		List<DeptDTO> list = session.selectList("DeptDynamicMapper.selectByDeptnoMulti", deptnoList);
		for(DeptDTO dto : list) {
			System.out.println(dto);
		}
		System.out.println();
		
		// 2. multi select2
		// select * from dept where deptno IN (값, 값2, ...)
		List<DeptDTO> deptnoList2 = Arrays.asList(new DeptDTO(10,"",""),
												  new DeptDTO(20,"",""),
												  new DeptDTO(30,"",""));
				
		List<DeptDTO> list2 = session.selectList("DeptDynamicMapper.selectByDeptnoMulti2", deptnoList2);
		for(DeptDTO dto : list2) {
			System.out.println(dto);
		}
		System.out.println();		
				
		// 3. multi delete
		// delete from dept where dept IN (값, 값2, ...)
		List<Integer> deptnoList3 = Arrays.asList(50, 51, 60);
		int n = session.delete("DeptDynamicMapper.deleteMulti", deptnoList3);
		System.out.println(n + " 개가 삭제됨");
		session.commit(); // 커밋 안하면 삭제 안됨
		
		// 3. multi update
		// update  dept set dname='개발' where deptno IN (값, 값2, ...)
		List<Integer> deptnoList4 = Arrays.asList(80, 12);
		int n2 = session.update("DeptDynamicMapper.deleteMulti", deptnoList4);
		System.out.println(n2 + " 개가 수정됨");
		session.commit(); // 커밋 안하면 삭제 안됨
		session.close();
		
		// 4. multi insert (한 번 더 돌리면 에러나니까 주석처리)
/*		insert all
     		into dept~ 
		    into dept~ 
		subquery; */
		
//		List<DeptDTO> deptnoList5 = Arrays.asList(new DeptDTO(90,"개발","서울"),
//				  new DeptDTO(91,"개발","경기"),
//				  new DeptDTO(92,"개발","부산"));
//		
//		int n3 = session.insert("DeptDynamicMapper.insertMulti", deptnoList5);
//		System.out.println(n3 + " 개가 저장됨");
//		session.commit();

		// 5. 조건 (단일 if만 지원, if~else (다중 조건) 지원 안됨. 대신 choose문을 지원 (조건이 여러개, switch와 비슷)
		 // 파라미터(dname) 값이 null이냐 아니냐에 따라서 다음 2가지 sql문 중 하나를 만듦
		 // 1) select * from dept where dname = 값; 
		 // 2) 또는 select * from dept 
		System.out.println();
		
		String dname = "개발"; // 아니면 null
//		String dname = null;
		List<DeptDTO> list4 = 
				session.selectList("DeptDynamicMapper.selectAllorDname", dname);
		for(DeptDTO dto : list4) {
			System.out.println(dto);
		}
		System.out.println();
		
		// 6. 다중 조건
		/*
		 * dname = 값 에 따라서 select 결과가 달라짐
		 * 
		 * dname 값이 '개발' ==> 10, 20, 30 검색
		 * 			where deptno IN(10, 20, 30)
		 * dname 값이 '영업부' ==> 40 검색
		 * 			where deptno = 40
		 * dname 값이 모두 만족하지 않으면 ==> 90, 91, 92 검색
		 * 			where deptno IN(90, 91, 92)
		 */
		HashMap<String, String> map = new HashMap<>();
		map.put("dname", "개발"); // 영업부, 그 이외 값
	//	map.put("dname", "영업부"); // 결과값 40
	//	map.put("dname", "xxx"); // 결과값  90, 91, 92
		
		List<DeptDTO> list5 = 
				session.selectList("DeptDynamicMapper.selectByDnameChoose", map);
		for(DeptDTO dto : list5) {
			System.out.println(dto);
		}
		System.out.println();
		
		session.close();
	}

}
