package com.shop.controller;

import com.shop.dto.CustDTO;
import com.shop.dto.NCPDTO;
import com.shop.service.CustService;
import com.shop.util.CFRUtil;
import com.shop.util.FileUploadUtil;
import com.shop.util.OCRUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class NCPController {
    @Value("${imglocation}")
    String imgpath;
    @RequestMapping("/ocr")
    public String register(Model model) {
        model.addAttribute("center","ocr");
        return "main";
    }

    @RequestMapping("/ocrimpl")
        public String ocrimpl(Model model, NCPDTO ncpdto) {

        String imgname = FileUploadUtil.saveFile(ncpdto.getImg(), imgpath);
        System.out.println(imgname);
        String result = OCRUtil.getText(imgname, imgpath);
        Map<String,String>map=null;
        try {
            map=OCRUtil.getConpanyinfo(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("imgname",imgname);
        model.addAttribute("name", map.get("name"));
        model.addAttribute("num", map.get("num"));

        model.addAttribute("center","ocrimpl");
        return "main";
    }

    @RequestMapping("/cfrimpl")
    public String cfrimpl(Model model, String imgname) throws Exception {
        String result = CFRUtil.getText(imgpath, imgname);
        Map<String, String> map = CFRUtil.getFaceInfo2(result);
        model.addAttribute("imgname", imgname);
        System.out.println("imgname = " + imgname);
        model.addAttribute("gender",map.get("gender"));
        model.addAttribute("age",map.get("age"));
        model.addAttribute("emotion",map.get("emotion"));
        model.addAttribute("pose",map.get("pose"));

        model.addAttribute("center","cfrimpl");
        return "main";
    }


    @RequestMapping("/card")
    public String card(Model model) {
        model.addAttribute("center","card");
        return "main";
    }

    @RequestMapping("/cardimpl")
    public String cardimpl(Model model, NCPDTO ncpdto) {

        String imgname = FileUploadUtil.saveFile(ncpdto.getImg(), imgpath);
        System.out.println(imgname);
        String result = OCRUtil.getText(imgname, imgpath);
        Map<String,String>map=null;
        try {
            map=OCRUtil.getCardInfo(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("imgname",imgname);
        model.addAttribute("valid", map.get("valid"));
        model.addAttribute("num", map.get("num"));

        model.addAttribute("center","cardimpl");
        return "main";
    }



}