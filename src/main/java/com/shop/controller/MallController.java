package com.shop.controller;

import com.github.pagehelper.PageInfo;
import com.shop.dto.CustDTO;
import com.shop.dto.ItemDTO;
import com.shop.dto.MallDTO;
import com.shop.service.ItemService;
import com.shop.service.MallService;
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
@RequestMapping("/mall")
public class MallController {
  @Autowired
  MallService mallService;

  @Value("${imglocation}")//application.properties
  String custdir;
  String dir= "/mall/";
  @RequestMapping("")
  public String item(Model model){ //left 와 center 영역만 바꿔줌
    model.addAttribute("left",dir+"left");
    model.addAttribute("center",dir+"center");
    return "main";
  }
  @RequestMapping("/add")
  public String add(Model model){ //left 와 center 영역만 바꿔줌
    model.addAttribute("left",dir+"left"); //item의 left html
    model.addAttribute("center",dir+"add");
    return "main";
  }
  @RequestMapping("/detail")//custlist 화면 출력
  public String detail(Model model, Long id){
    MallDTO obj = null;//받을 준비
    try {
      obj = mallService.get(id);
      System.out.println(obj);
      model.addAttribute("dmall",obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("left",dir+"left"); //cust의 left html
    model.addAttribute("center",dir+"detail");

    return "main";
  }
  @RequestMapping("/addimpl") //cust의  add
  public String addimpl(Model model, MallDTO obj){
    String imgname = obj.getImg().getOriginalFilename();//이미지의 이름
    obj.setImgname(imgname);//데이터베이스의 imgname 셋팅
    //화면에서 업로드한 파일이 db에 저장
    try {
      FileUploadUtil.saveFile(obj.getImg(), custdir);
      mallService.register(obj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/mall/getpage";
  }
//  @RequestMapping("/get")
//  public String get(Model model){ //left 와 center 영역만 바꿔줌
//
//    List<MallDTO> list = null;
//    try {
//      list = mallService.get();
//      model.addAttribute("mlist",list);
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
////        try {
////            List<ItemDTO> list = service.get();
////            model.addAttribute("itemList", list);
////        }catch (Exception e){
////            throw new RuntimeException(e);
////        }
//
//    model.addAttribute("left",dir+"left"); //cust의 left html
//    model.addAttribute("center",dir+"get");
//    return "main";
//  }

  @GetMapping("/getpage")
  public String getpage(@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {//기본 페이지 1
    PageInfo<MallDTO> p = new PageInfo<>(mallService.getPage(pageNum), 5);
    model.addAttribute("malls", p);
    model.addAttribute("left", dir+"left");
    model.addAttribute("center", dir+"getpage");

    return "/main";
  }

  @RequestMapping("/updateimpl") //cust의  add
  public String updateimpl(Model model, MallDTO obj) throws Exception {
    System.out.println(obj);

    if(obj.getImg().getOriginalFilename().equals("")){//새로운 이미지 없을 때
      mallService.modify(obj);//기존이미지이름으로 업데이트된다.
    }else {//새로운 이미지 있을 때
      String newimgname = obj.getImg().getOriginalFilename();//new 이미지 이름
      FileUploadUtil.saveFile(obj.getImg(),custdir);//이미지를 저장
      obj.setImgname(newimgname);
      mallService.modify(obj);
    }

    return "redirect:detail?id=" + obj.getId();//상세페이지로
  }
  @RequestMapping("/deleteimpl") //cust의  add
  public String deleteimpl(Model model, Long id){
    try {
      mallService.remove(id);//left 와 center 영역만 바꿔줌
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return "redirect:/mall/getpage";
  }
}
