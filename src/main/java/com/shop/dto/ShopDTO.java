package com.shop.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ShopDTO {
  private String id;
  private String name;
  private String number;
  private String address;
  private Time opentime;
  private Time closetime;
  private BigDecimal lat;
  private BigDecimal lng;
  private String imgname;
  private MultipartFile img;
}
