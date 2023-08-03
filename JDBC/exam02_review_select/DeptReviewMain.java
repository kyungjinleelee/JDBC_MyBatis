package exam02_review_select;

import java.util.List;

public class DeptReviewMain {

	public static void main(String[] args) throws Exception {

		// DB 연동
		// DB연동
		DeptReviewServiceImpl service = new DeptReviewServiceImpl();
		List<DeptReviewDTO> list = service.select();
		for (DeptReviewDTO dto : list) {
			System.out.println(dto);
		}
	}

}
