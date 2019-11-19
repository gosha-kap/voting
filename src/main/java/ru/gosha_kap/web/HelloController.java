package ru.gosha_kap.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gosha_kap.model.Restaurant;
import ru.gosha_kap.service.RestaurantService;

import java.util.List;

@Controller
public class HelloController {
    @Autowired
    private RestaurantService service;

    @GetMapping("/")
    public String root() {
            return "index";
    }



}
