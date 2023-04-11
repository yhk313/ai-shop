package com.shop.service;

import com.shop.dto.ShopItemDTO;
import com.shop.frame.MyService;
import com.shop.mapper.ShopItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopItemSevice implements MyService<Integer, ShopItemDTO> {
  @Autowired
  ShopItemMapper shopItemMapper;

  @Override
  public void register(ShopItemDTO shopItemDTO) throws Exception {

  }

  @Override
  public void modify(ShopItemDTO shopItemDTO) throws Exception {

  }

  @Override
  public void remove(Integer integer) throws Exception {

  }

  @Override
  public ShopItemDTO get(Integer integer) throws Exception {
    return null;
  }

  @Override
  public List<ShopItemDTO> get() throws Exception {
    return null;
  }
   public List<ShopItemDTO> getByShopId(String shop_id) throws Exception {
    return shopItemMapper.shopitemall(shop_id);
  }


}
