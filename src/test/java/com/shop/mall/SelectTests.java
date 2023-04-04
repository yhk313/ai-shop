package com.shop.mall;

import com.shop.dto.ItemDTO;
import com.shop.dto.MallDTO;
import com.shop.service.ItemService;
import com.shop.service.MallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SelectTests {
	@Autowired
	MallService service;
	@Test
	void contextLoads() {
		MallDTO obj = null;
		try {
			obj = service.get(1L);
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("입력 시 오류");
		}
	}

}
