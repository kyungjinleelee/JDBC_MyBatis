package exam04_review_update;

import java.util.List;

public class DeptReviewMain {

	public static void main(String[] args) throws Exception {

		// DB 연동 -insert 작업
		
//	int deptno = 99;
//		String dname ="관리";
//		String loc = "서울";
//		DeptReviewDTO xxx = new DeptReviewDTO(deptno, dname, loc);
//		// 서비스 연동
//		DeptReviewServiceImpl service = new DeptReviewServiceImpl();
//		int n = service.insert(xxx);
//		System.out.println(n+" 개가 저장되었습니다.");
		
		
		// DB 연동 - update 작업
		int deptno = 99;
		String dname = "관리부";
		String loc = "서울시";
		
		DeptReviewDTO xxx = new DeptReviewDTO(deptno, dname, loc);
		DeptReviewServiceImpl service = new DeptReviewServiceImpl();
		int n = service.update(xxx);
		System.out.println(n+" 개가 수정 되었습니다.");
		
		// DB 연동 - select 작업
		List<DeptReviewDTO> list = service.select();
		for (DeptReviewDTO dto : list) {
			System.out.println(dto);
		}
	}

}
