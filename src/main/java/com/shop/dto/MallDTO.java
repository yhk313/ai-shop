package com.shop.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MallDTO {
  private Long id;
  private String name;
  private String admin;
  private String number;
  private String address;
  private String opentime;
  private String closetime;
  private String imgname;
  private MultipartFile img;
}
