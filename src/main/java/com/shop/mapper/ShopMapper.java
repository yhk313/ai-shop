package com.shop.mapper;

import com.shop.dto.CartsDTO;
import com.shop.dto.MallDTO;
import com.shop.dto.ShopDTO;
import com.shop.dto.ShopItemDTO;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShopMapper extends MyMapper<String, ShopDTO> {


}
