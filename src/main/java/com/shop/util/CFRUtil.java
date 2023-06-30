package com.shop.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class CFRUtil {
  @Value("${CFR-client-key}")
  static String clientId;//애플리케이션 클라이언트 아이디값";
  @Value("${CFR-secret-key}")
  static String clientSecret;//애플리케이션 클라이언트 시크릿값";

  public  static String getText( String imgpath,String imgname){
    StringBuffer reqStr = new StringBuffer();
    String result = "";

    try {
      String paramName = "image"; // 파라미터명은 image로 지정
      String imgFile = imgpath+imgname;
      File uploadFile = new File(imgFile);
      String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/face"; // 얼굴 감지
      URL url = new URL(apiURL);
      HttpURLConnection con = (HttpURLConnection)url.openConnection();
      con.setUseCaches(false);
      con.setDoOutput(true);
      con.setDoInput(true);
      // multipart request
      String boundary = "---" + System.currentTimeMillis() + "---";
      con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
      con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
      con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
      OutputStream outputStream = con.getOutputStream();
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
      String LINE_FEED = "\r\n";
      // file 추가
      String fileName = uploadFile.getName();
      writer.append("--" + boundary).append(LINE_FEED);
      writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
      writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
      writer.append(LINE_FEED);
      writer.flush();
      FileInputStream inputStream = new FileInputStream(uploadFile);
      byte[] buffer = new byte[4096];
      int bytesRead = -1;
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, bytesRead);
      }
      outputStream.flush();
      inputStream.close();
      writer.append(LINE_FEED).flush();
      writer.append("--" + boundary + "--").append(LINE_FEED);
      writer.close();
      BufferedReader br = null;
      int responseCode = con.getResponseCode();
      if(responseCode==200) { // 정상 호출
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      } else {  // 오류 발생
        System.out.println("error!!!!!!! responseCode= " + responseCode);
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      }
      String inputLine;
      if(br != null) {
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
          response.append(inputLine);
        }
        br.close();
        System.out.println(response.toString());
        result = response.toString();
      } else {
        System.out.println("error !!!");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return result;

  }

  public static Map<String,String> getFaceInfo2(String json) throws Exception {
    HashMap<String, String> map = new HashMap<>();
    JSONParser jsonparser = new JSONParser();
    JSONObject global = (JSONObject)jsonparser.parse(json);//자바에서 객체로 사용할 수 있게함

    JSONArray faces = (JSONArray) global.get("faces");
    JSONObject obj = (JSONObject) faces.get(0);

    JSONObject gender = (JSONObject) obj.get("gender");
    String gender_value = (String)gender.get("value");

    JSONObject age = (JSONObject) obj.get("age");

    String age_value = (String)age.get("value");
    JSONObject emotion = (JSONObject) obj.get("emotion");
    String emotion_value = (String)emotion.get("value");

    JSONObject pose = (JSONObject) obj.get("pose");
    String pose_value = (String)pose.get("value");

    map.put("gender", gender_value);
    map.put("age", age_value);
    map.put("emotion", emotion_value);
    map.put("pose", pose_value);

    return map;
  }



}
