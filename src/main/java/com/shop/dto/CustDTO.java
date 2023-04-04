package com.shop.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CustDTO {
    private String id;
    private String pwd;
    private String name;


}
