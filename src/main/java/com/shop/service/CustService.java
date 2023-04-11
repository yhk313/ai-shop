package com.shop.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shop.dto.CustDTO;
import com.shop.dto.ItemDTO;
import com.shop.frame.MyService;
import com.shop.mapper.CustMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class CustService implements MyService<String, CustDTO> {
    @Autowired
    CustMapper custMapper;
    @Override
    public void register(CustDTO custDTO) throws Exception {
        custMapper.insert(custDTO);

    }

    @Override
    public void modify(CustDTO custDTO) throws Exception {
        custMapper.update(custDTO);

    }

    @Override
    public void remove(String s) throws Exception {
        custMapper.delete(s);

    }

    @Override
    public CustDTO get(String s) throws Exception {
        return custMapper.select(s);
    }

    @Override
    public List<CustDTO> get() throws Exception {
        return custMapper.selectall();
    }

    public Page<CustDTO> search(int pageNo, String txt) throws Exception{
        PageHelper.startPage(pageNo, 3);
        return custMapper.search(txt);
    }
    public Page<CustDTO> getPage(int pageNo) throws Exception {//페이징처리
        PageHelper.startPage(pageNo, 10);// 10개만 들어옴
        return custMapper.getPage();
    }
}
