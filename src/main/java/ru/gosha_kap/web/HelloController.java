package ru.gosha_kap.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/")
    public String readMeInfo() {
            return "index";
    }



}
