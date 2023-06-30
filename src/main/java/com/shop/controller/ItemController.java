package com.shop.controller;

import com.github.pagehelper.PageInfo;
import com.shop.dto.CustDTO;
import com.shop.dto.ItemDTO;
import com.shop.service.ItemService;
import com.shop.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
  @Autowired
  ItemService itemService;
  String dir = "/item/";

  @Value("${imglocation}")//application.properties
  String custdir;

  @RequestMapping("")
  public String item(Model model) { //left 와 center 영역만 바꿔줌
    model.addAttribute("left", dir + "left");
    model.addAttribute("center", dir + "center");
    return "main";
  }

  @RequestMapping("/add")
  public String add(Model model) {
    model.addAttribute("left", dir + "left"); //item의 left html
    model.addAttribute("center", dir + "add");
    return "main";
  }

  @RequestMapping("/get")
  public String get(Model model) {

    List<ItemDTO> list = null;
    try {
      list = itemService.get();
      model.addAttribute("ilist", list);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
//        try {
//            List<ItemDTO> list = service.get();
//            model.addAttribute("itemList", list);
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }

    model.addAttribute("left", dir + "left"); //cust의 left html
    model.addAttribute("center", dir + "get");
    return "main";
  }

  @GetMapping("/getpage")
  public String getpage(@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {//기본 페이지 1
    PageInfo<ItemDTO> p = new PageInfo<>(itemService.getPage(pageNum), 10);
    model.addAttribute("items", p);
    model.addAttribute("left", dir + "left");
    model.addAttribute("center", dir + "getpage");

    return "/main";
  }

  @RequestMapping("/detail")
  public String detail(Model model, int id) {
    ItemDTO obj = null;//받을 준비
    try {
      obj = itemService.get(id);
      model.addAttribute("ditem", obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("left", dir + "left"); //cust의 left html
    model.addAttribute("center", dir + "detail");

    return "main";
  }

  @RequestMapping("/addimpl")
  public String registerimpl(Model model, ItemDTO obj) {


    //화면에서 업로드한 파일이 db에 저장
    try {
      String newFileName = FileUploadUtil.saveFile(obj.getImg(), custdir);
      obj.setImgname(newFileName);
      itemService.register(obj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:getpage";
  }

  @RequestMapping("/updateimpl") //cust의  add
  public String updateimpl(Model model, ItemDTO obj) throws Exception {// 신규 이미지가 있을 수도 없을 수도 있음
    System.out.println(obj);

    if (obj.getImg().getOriginalFilename().equals("")) {//새로운 이미지 없을 때
      itemService.modify(obj);//기존이미지이름으로 업데이트된다.
    } else {//새로운 이미지 있을 때
      String newimg = FileUploadUtil.saveFile(obj.getImg(), custdir);//이미지를 저장
      obj.setImgname(newimg);
      itemService.modify(obj);
    }

    return "redirect:detail?id=" + obj.getId();//상세페이지로
  }


  @RequestMapping("/deleteimpl")
  public String deleteimpl(Model model, int id) {
    try {
      itemService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return "redirect:getpage";
  }

  @RequestMapping("/addcart")
  public String addcart(Model model, int id) {
    ItemDTO obj = null;//받을 준비
    try {
      obj = itemService.get(id);
      model.addAttribute("ditem", obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("left", dir + "left"); //cust의 left html
    model.addAttribute("center", dir + "addcart");

    return "main";
  }

  @RequestMapping("/searchimpl")
  public String search(@RequestParam(required = false, defaultValue = "1") int pageNum, Model model, String txt) {
    try {
      PageInfo<ItemDTO> searchList = new PageInfo<>(itemService.search(pageNum, txt), 10);
      model.addAttribute("items", searchList);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("left", dir + "left"); //cust의 left html
    model.addAttribute("center", dir + "getpage");

    return "main";
  }


}