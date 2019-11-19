package ru.gosha_kap.web.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gosha_kap.service.MenuService;
import ru.gosha_kap.to.RestrntFullInfo;
import ru.gosha_kap.to.RestrntMonthRating;
import ru.gosha_kap.util.MenuUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/monthRate")
    public List<RestrntMonthRating> getAllShort(){

        List<RestrntMonthRating> restaurants = new ArrayList<>();
        restaurants.add(new RestrntMonthRating(1,"name1",10));
        restaurants.add(new RestrntMonthRating(2,"name2",12));
        restaurants.add(new RestrntMonthRating(3,"name3",15));
        return restaurants.stream().sorted(Comparator.comparingInt(RestrntMonthRating::getMonthRate)).collect(Collectors.toList());
    }
    @GetMapping("/all")
    public List<RestrntFullInfo> getAllDetail(){
        return MenuUtil.getAll(menuService.getAllUpdated());

    }
}
