package com.shop.ncp;

import com.shop.util.ChatBotUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ChatbotTest {

	@Test
	void contextLoads() throws IOException {
		String str="니이름이뭐니";
		String result = ChatBotUtil.chat(str);
		System.out.println("result = " + result);
	}

}
