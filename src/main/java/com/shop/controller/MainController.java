package com.shop.controller;

import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    CustService custService;

    @RequestMapping("/")
    public String main() {
        return "main";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("center","register");
        return "main";
    }

    @RequestMapping("/registerimpl")
    public String registerimpl(Model model,CustDTO cust, HttpSession session) {
        try {
            custService.register(cust);
            session.setAttribute("logincust", cust);//로그인 유지.
            model.addAttribute("rcust", cust);
            model.addAttribute("center","registerok");
        } catch (Exception e) {
            model.addAttribute("center","registerfail");
        }

        return "main";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if(session != null){
            session.invalidate();//세션만 없애면 로그아웃
        }
        return "main";
    }

    @RequestMapping("/loginimpl")
    public String loginimpl(Model model, HttpSession session, String id, String pwd) {
        CustDTO cust = null;
        String center = "loginok";
        try {
            cust = custService.get(id);
            if (cust == null) {
                center = "loginfail";
            } else {
                if (cust.getPwd().equals(pwd)) {
                    session.setAttribute("logincust", cust);
                    if (cust.getId().equals("admin")) {
                        session.setAttribute("userRole", "admin");
                    } else {
                        session.setAttribute("userRole", "user");
                    }
                    center = "loginok";
                }
            }
        } catch (Exception e) {
            center = "loginfail";
        }
        model.addAttribute("center", center);
        return "main";
    }


}