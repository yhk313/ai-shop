package com.shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  //uploadPath프로퍼티값을 읽어온다
  @Value("${uploadpath}")
  String uploadPath;// 이미지가 있는곳으로 경로 등록

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/img/**").addResourceLocations(uploadPath);//경로를 등록, html 에는 img 이름으로
    //로컬컴퓨터에 저장된 파일을 읽어올 root경로
  }
}

