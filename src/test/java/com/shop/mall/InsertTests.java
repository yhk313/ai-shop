package com.shop.mall;

import com.shop.dto.CartsDTO;
import com.shop.dto.CustDTO;
import com.shop.dto.MallDTO;
import com.shop.service.CartsService;
import com.shop.service.CustService;
import com.shop.service.MallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;

@SpringBootTest
class InsertTests {
	@Autowired
	MallService service;
	@Test
	void contextLoads() {
		MallDTO obj = new MallDTO(null,"인규네2","최인규","01000000000","서울","12:50","20:00","img",null);
		try {
			service.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("입력 시 오류");
		}
	}

}
