package com.shop.mapper;

import com.github.pagehelper.Page;
import com.shop.dto.CustDTO;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CustMapper extends MyMapper<String, CustDTO> {
    Page<CustDTO> getPage() throws Exception;// 페이징처리 외부라이브러리 이용


}
