package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    // GTP http://lovalhost:8080/
    @GetMapping("/")
    public void home () {
        System.out.println("home");
        this.contact();
    }

    // GTP http://lovalhost:8080/about
    @GetMapping("/about")
    public void about () {
        System.out.println("about");
        this.contact();
    }

    public void contact() {
        System.out.println("contact");
    }
}
