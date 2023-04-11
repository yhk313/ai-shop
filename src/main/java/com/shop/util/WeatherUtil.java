package com.shop.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherUtil {

  static String skey = "O2%2BFaNjXQLZ9GqP8qpF7r8Y1uDBkeviLiUmQo6rJcaBu9y7d%2FKjeVUvnpDnfEXU%2F9NH7DWdC%2By%2BkHGUmNMUl5Q%3D%3D";


  public static String getWeatherInfo(String loc) throws Exception {
    Date date = new Date();
    SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");


    String result = "";
    StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst"); /*URL*/
    urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + skey); /*Service Key*/
    urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
    urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
    urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
    urlBuilder.append("&" + URLEncoder.encode("stnId","UTF-8") + "=" + URLEncoder.encode(loc, "UTF-8")); /*108 전국, 109 서울, 인천, 경기도 등 (활용가이드 하단 참고자료 참조)*/
    urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode(s.format(date)+"0600", "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600 (1800)-최근 24시간 자료만 제공*/
    URL url = new URL(urlBuilder.toString());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    System.out.println("Response code: " + conn.getResponseCode());
    BufferedReader rd;
    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
      rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    } else {
      rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    }
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = rd.readLine()) != null) {
      sb.append(line);
    }
    rd.close();
    conn.disconnect();
    System.out.println(sb.toString());
    result = sb.toString();




    JSONParser jsonparser = new JSONParser();
    JSONObject global = (JSONObject)jsonparser.parse(result);

    JSONObject response = (JSONObject) global.get("response");
    JSONObject body = (JSONObject) response.get("body");

    JSONObject items = (JSONObject) body.get("items");
    JSONArray item = (JSONArray) items.get("item");
    JSONObject obj = (JSONObject) item.get(0);
    String wfSv = (String) obj.get("wfSv");
    System.out.println("wfSv = " + wfSv);

    return wfSv;


  }

}
