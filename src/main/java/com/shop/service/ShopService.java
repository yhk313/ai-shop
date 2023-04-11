package com.shop.service;

import com.shop.dto.ShopDTO;
import com.shop.frame.MyService;
import com.shop.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService implements MyService<String, ShopDTO> {
  @Autowired
  ShopMapper shopMapper;
  @Override
  public void register(ShopDTO shopDTO) throws Exception {

  }

  @Override
  public void modify(ShopDTO shopDTO) throws Exception {

  }

  @Override
  public void remove(String s) throws Exception {

  }

  @Override
  public ShopDTO get(String id) throws Exception {
    return shopMapper.select(id);
  }

  @Override
  public List<ShopDTO> get() throws Exception {
    return null;
  }
}
