package com.shop.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ItemDTO {
    private int id;
    private String name;
    private int price;
    private String imgname;
    private Date rdate;
    //화면에서 올라오는 이미지 받기
    private MultipartFile img;//item add.html file name = img 똑같게
}
