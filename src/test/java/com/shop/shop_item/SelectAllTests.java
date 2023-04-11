package com.shop.shop_item;

import com.shop.dto.ItemDTO;
import com.shop.dto.ShopItemDTO;
import com.shop.service.ItemService;
import com.shop.service.ShopItemSevice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SelectAllTests {
	@Autowired
	ShopItemSevice service;
	@Test
	void contextLoads() {
		List<ShopItemDTO> list = null;

		try {
			list = service.getByShopId("s001");
			for(ShopItemDTO c:list){
				System.out.println(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("입력 시 오류");
		}
	}

}
