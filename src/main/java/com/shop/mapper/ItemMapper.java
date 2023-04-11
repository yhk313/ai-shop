package com.shop.mapper;

import com.github.pagehelper.Page;
import com.shop.dto.CustDTO;
import com.shop.dto.ItemDTO;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ItemMapper extends MyMapper<Integer, ItemDTO> {
    public Page<ItemDTO> search(String txt) throws Exception;
    Page<ItemDTO> getPage() throws Exception;// 페이징처리 외부라이브러리 이용
}
