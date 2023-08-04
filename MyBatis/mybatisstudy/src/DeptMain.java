import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DeptMain {

	public static void main(String[] args) throws Exception{

		// Configuration.xml 파일 읽기
		String resource = "Configuration.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
	// 단일행 (findByDeptno) 호출
		DeptDTO dto = session.selectOne("DeptMapper.findByDeptno", 10); // (namespace이름.id값)
		// deptno가 10인 애를 찾아서 deptdto에 저장한 후 반환하기
		System.out.println(dto);
		System.out.println("--------------------");
		
	// 복수행(findAll) 호출/ 여러개 리턴이라 list 씀/ all 이라 #{} 없어서 하나만 쓰면 됨
		List<DeptDTO> list = session.selectList("DeptMapper.findAll");
		for (DeptDTO xxx : list) {
			System.out.println(xxx);
		}
		
	// findByDeptnoAndDname
		DeptDTO dto2 = new DeptDTO();
		dto2.setDeptno(10);
		dto2.setDname("인사"); // 10번이거나 인사인 것 찾기 
		
		List<DeptDTO> list2 = session.selectList("DeptMapper.findByDeptnoAndDname", dto2);
		for (DeptDTO xxx : list2) {
			System.out.println(xxx);
		}
		
		// 그 다음 SqlSession 닫기 (맨 마지막에서 한번)
		System.out.println("---------------------");
		
	// findByDeptnoAndDnameMap
		HashMap<String, Object> map = 
				new HashMap<>();
		map.put("xxx", 10);
		map.put("yyy", "인사");
		
		List<DeptDTO> list3 = 
				session.selectList("DeptMapper.findByDeptnoAndDnameMap", map);
		
		for (DeptDTO xxx : list3) {
			System.out.println(xxx);
		}
		
		System.out.println("---------------------");
		
		// findAllPage
		RowBounds bounds = new RowBounds(0, 3);
		List<DeptDTO> list4 = 
				session.selectList("DeptMapper.findAllPage", null, bounds);
		
		for (DeptDTO xxx : list4) {
			System.out.println(xxx);
		}
		
		session.close();
	
		
		
	}

}
