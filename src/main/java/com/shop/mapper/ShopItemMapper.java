package com.shop.mapper;

import com.shop.dto.ShopDTO;
import com.shop.dto.ShopItemDTO;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShopItemMapper extends MyMapper<Integer, ShopItemDTO> {
 List<ShopItemDTO> shopitemall(String shop_id) throws Exception;

}
