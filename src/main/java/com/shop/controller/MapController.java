package com.shop.controller;

import com.shop.dto.MallDTO;
import com.shop.dto.ShopDTO;
import com.shop.dto.ShopItemDTO;
import com.shop.service.ShopItemSevice;
import com.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MapController {

  @Autowired
  ShopService shopService;

  @Autowired
  ShopItemSevice shopItemSevice;
  String dir= "/shop/";
  @RequestMapping("/map1")
  public String map1(Model model) {
    model.addAttribute("center","map1");
    return "main";
  }

  @RequestMapping("/map2")
  public String map2(Model model) {
    model.addAttribute("center","map2");
    return "main";
  }
  @RequestMapping("/shopdetail")
  public String shopdetail(Model model, String id) {
    ShopDTO obj = null;//받을 준비
    System.out.println(id);
    List<ShopItemDTO> list = null;
    try {
      obj = shopService.get(id);
      list = shopItemSevice.getByShopId(id);
      System.out.println(obj);
      System.out.println("list = " + list);
      model.addAttribute("shop",obj);
      model.addAttribute("slist",list);

      System.out.println(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    model.addAttribute("center",dir+"shopdetail");

    return "main";
  }
}
