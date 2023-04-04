package com.shop.cust;

import com.shop.service.CustService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeleteTests {
	@Autowired
	CustService custService;
	@Test
	void contextLoads() {

		try {
			custService.remove("124");
			System.out.println("삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("삭제 시 오류");
		}
	}

}
