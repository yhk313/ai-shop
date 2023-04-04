package com.shop.mapper;

import com.github.pagehelper.Page;
import com.shop.dto.ItemDTO;
import com.shop.dto.MallDTO;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MallMapper extends MyMapper<Long, MallDTO> {
  Page<MallDTO> getPage() throws Exception;
}
