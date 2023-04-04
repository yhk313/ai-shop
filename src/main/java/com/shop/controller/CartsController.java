package com.shop.controller;

import com.github.pagehelper.PageInfo;
import com.shop.dto.CartsDTO;
import com.shop.dto.CustDTO;
import com.shop.service.CartsService;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartsController {

    @Autowired
    CartsService service;
    String dir= "/cart/";


//    @RequestMapping("")
//    public String cart(Model model, String id) {
//        List<CartsDTO> list = null;
//        try {
//            list = service.cartsall(id);
//            model.addAttribute("mycarts",list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        model.addAttribute("center", dir+"center");
//        return "main";
//    }

    @GetMapping("")
    public String getpage(String id, @RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {//기본 페이지 1
        PageInfo<CartsDTO> p = new PageInfo<>(service.cartsallpage(pageNum, id), 3);
        model.addAttribute("items", p);
//        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"getpage");

        return "/main";
    }


}