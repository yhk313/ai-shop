package com.shop.controller;

import com.github.pagehelper.PageInfo;
import com.shop.dto.CustDTO;
import com.shop.dto.ItemDTO;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cust")
public class CustController {

    @Autowired
    CustService custService;
    String dir= "/cust/";

    @RequestMapping("")
    public String cust(Model model){
        model.addAttribute("left",dir+"left");
        model.addAttribute("center",dir+"center");
        String version = org.springframework.core.SpringVersion.getVersion();
        System.out.println("version : " + version);
        return "main";
    }
    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("left",dir+"left"); //cust의 left html
        model.addAttribute("center",dir+"add");
        return "main";
    }

    @RequestMapping("/addimpl") //cust의  add
    public String addimpl(Model model, CustDTO cust){
        try {
            custService.register(cust);//left 와 center 영역만 바꿔줌
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/cust/getpage";
    }

    @RequestMapping("/get")//custlist 화면 출력
    public String get(Model model){
        List<CustDTO> list = null;
        try {
            list = custService.get();
            model.addAttribute("clist",list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("left",dir+"left"); //cust의 left html
        model.addAttribute("center",dir+"get");

        return "main";
    }

    @RequestMapping("/detail")//custlist 화면 출력
    public String detail(Model model, String id){
        CustDTO obj = null;//받을 준비
        try {
            obj = custService.get(id);
            System.out.println(obj);
            model.addAttribute("dcust",obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("left",dir+"left"); //cust의 left html
        model.addAttribute("center",dir+"detail");

        return "main";
    }

    @GetMapping("/getpage")
    public String getpage(@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {//기본 페이지 1
        PageInfo<CustDTO> p = new PageInfo<>(custService.getPage(pageNum), 10);
        model.addAttribute("users", p);
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"getpage");

        return "/main";
    }

    @RequestMapping("/updateimpl") //cust의  add
    public String updateimpl(Model model, CustDTO cust){
        try {
            custService.modify(cust);//left 와 center 영역만 바꿔줌
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/cust/detail?id=" + cust.getId();
    }
    @RequestMapping("/deleteimpl") //cust의  add
    public String deleteimpl(Model model, String id){
        try {
            custService.remove(id);//left 와 center 영역만 바꿔줌
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/cust/getpage";
    }
    @RequestMapping("/searchimpl")
    public String searchimpl(@RequestParam(required = false, defaultValue = "1") int pageNum, Model model, String txt) {
        try {
            PageInfo<CustDTO> searchList = new PageInfo<>(custService.search(pageNum, txt), 10);
            model.addAttribute("users", searchList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("left", dir + "left"); //cust의 left html
        model.addAttribute("center", dir + "getpage");

        return "main";
    }
}