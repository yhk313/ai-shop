package com.shop.controller;

import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import com.shop.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
public class AJAXController {
    @Value("${imglocation}")
    String imgdir;
    @PostMapping("/saveimg")
    public String saveimg(Model model, MultipartFile file){
        String filename = FileUploadUtil.saveFile(file, imgdir);
        return filename;
    }

}
