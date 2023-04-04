package com.shop.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shop.dto.ItemDTO;
import com.shop.dto.MallDTO;
import com.shop.frame.MyService;
import com.shop.mapper.MallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallService implements MyService<Long, MallDTO> {
  @Autowired
  MallMapper mallmapper;
  @Override
  public void register(MallDTO mallDTO) throws Exception {
  mallmapper.insert(mallDTO);
  }

  @Override
  public void modify(MallDTO mallDTO) throws Exception {
  mallmapper.update(mallDTO);
  }

  @Override
  public void remove(Long id) throws Exception {
  mallmapper.delete(id);
  }

  @Override
  public MallDTO get(Long id) throws Exception {
    return mallmapper.select(id);
  }

  @Override
  public List<MallDTO> get() throws Exception {
    return mallmapper.selectall();
  }
  public Page<MallDTO> getPage(int pageNo) throws Exception {//페이징처리
    PageHelper.startPage(pageNo, 4);// 10개만 들어옴
    return mallmapper.getPage();
  }
}
