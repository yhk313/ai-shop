package com.shop.ncp;

import com.shop.util.OCRUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OCRTest2 {
	@Value("${imglocation}")
	String imgpath;
	@Test
	void contextLoads() throws ParseException {
	 String imgname = "card01.jpg";
	 String result = OCRUtil.getText(imgname,imgpath);
		System.out.println(result);
		JSONParser jsonparser = new JSONParser();
		JSONObject global = (JSONObject)jsonparser.parse(result);//자바에서 객체로 사용할 수 있게함

		JSONArray images = (JSONArray) global.get("images");// 전체에서 images 꺼낸다.
		JSONObject jo1 = (JSONObject) images.get(0);// 배열안에서 object꺼내기

		JSONArray fields = (JSONArray) jo1.get("fields");//filelds 꺼내기
		JSONObject obj = (JSONObject) fields.get(0);
		String name = (String)obj.get("inferText");
		System.out.println(name);


		JSONObject title = (JSONObject) jo1.get("title");//title 꺼내기
		String num = (String)title.get("inferText");
		System.out.println(num);
	}
	}

