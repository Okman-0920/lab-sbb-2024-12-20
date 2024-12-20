package com.mysite.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MainController {
    // GET http://localhost:8080/
    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    // GET http://localhost:8080/about
    @GetMapping("/about")
        public void about() {
        System.out.println("about");
    }
}