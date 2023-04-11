package com.shop.controller;

import com.shop.util.WeatherUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeatherController {
  @RequestMapping("/weather")
  public String weather(Model model) throws Exception {
    model.addAttribute("center","weather");
    return "main";
  }

  @RequestMapping("/weatherimpl")
  public String weather(Model model, String loc) throws Exception {
    System.out.println("loc = " + loc);
    String result = WeatherUtil.getWeatherInfo(loc);
    model.addAttribute("loc", loc);
    model.addAttribute("result", result);
    model.addAttribute("center","weather");
    return "main";
  }
}
