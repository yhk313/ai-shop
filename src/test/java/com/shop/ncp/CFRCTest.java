package com.shop.ncp;

import com.shop.util.CFRCUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CFRCTest {
	@Value("${imglocation}")
	String imgpath;
	@Test
	void contextLoads() throws ParseException {
		String imgname = "5fbaac3b-e00d-4b2f-9ee1-b.jpg";
		String result = CFRCUtil.getText(imgpath, imgname);
		System.out.println("result = " + result);

		JSONParser jsonparser = new JSONParser();
		JSONObject global = (JSONObject)jsonparser.parse(result);

		JSONArray faces = (JSONArray) global.get("faces");
		JSONObject obj = (JSONObject) faces.get(0);

		JSONObject celebrity = (JSONObject) obj.get("celebrity");

		String value = (String)celebrity.get("value");
		System.out.println(value);
	}

}
