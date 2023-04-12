package com.shop.controller;

import com.github.pagehelper.PageInfo;
import com.shop.dto.CartsDTO;
import com.shop.dto.CustDTO;
import com.shop.service.CartsService;
import com.shop.service.CustService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
@Slf4j
public class CartsController {

    @Autowired
    CartsService cartsService;
    String dir= "/cart/";

    @GetMapping("")
    public String getpage(String id, @RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {//기본 페이지 1
        String userId = id;
        log.info("get page {}", userId);

        PageInfo<CartsDTO> p = new PageInfo<>(cartsService.cartsallpage(pageNum, userId), 5);//버튼수?
        List<CartsDTO> mycarts = null;//해당사용자 카트 정보 전부.
        mycarts = cartsService.cartsall(userId);

        model.addAttribute("items", p);//페이지처리
        model.addAttribute("mycarts", mycarts);//페이지처리
//        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"getpage");

        return "/main";
    }

    @PostMapping("/addcartimpl")
    public String addcartimpl(CartsDTO carts) throws Exception {//기본 페이지 1
        cartsService.register(carts);
        return "redirect:/cart?id="+carts.getCust_id();// 로그인 유저 카트리스트로 넘어감.
    }

//    @RequestMapping("/delete")
//    public String delete(Model model, int id, HttpSession httpSession){
//        int cartId = id;
//        try {
//            CustDTO logincust = (CustDTO) httpSession.getAttribute("logincust");
//            cartsService.remove(cartId);
//            return "redirect:/cart?id="+logincust.getId();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @RequestMapping("/deleteimpl")
    public String deleteimpl(Model model, int id, String cid) throws Exception {
        System.out.println(id +""+cid);
        cartsService.remove(id);
        return "redirect:/cart?id="+cid;
    }
}