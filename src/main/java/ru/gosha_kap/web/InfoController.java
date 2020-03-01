package ru.gosha_kap.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gosha_kap.service.MenuService;
import ru.gosha_kap.to.RestrntFullInfo;
import ru.gosha_kap.to.RestrntVoteHistory;
import ru.gosha_kap.util.MenuUtil;

import java.util.List;

@RestController
@RequestMapping("rest/info")
public class InfoController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<RestrntFullInfo> getAllToday() {
        return MenuUtil.getAll(menuService.getTodayMenus());
    }

    @GetMapping("/menu/{menuID}")
    public RestrntFullInfo getOne(@PathVariable int menuID){
        return MenuUtil.createRestaurantInfo(menuService.getMenu(menuID));
    }

     @GetMapping("/votehist/{restaurantId}")
    public List<RestrntVoteHistory> getHistoryForOne(@PathVariable int restaurantId) {
        return MenuUtil.getVoteHistory(menuService.getHistoryForOne(restaurantId));
    }

    @GetMapping("/votehist")
    public List<RestrntVoteHistory> getHistoryVoting() {
        return MenuUtil.getVoteHistory(menuService.getHistoryVoting());
    }

    @GetMapping("/top10")
    public List<RestrntFullInfo> getPopularsMenus() {
        return MenuUtil.getAll(menuService.getPopularsMenus());
    }

    @GetMapping("/top10/{restaurantId}")
    public List<RestrntFullInfo> getPopularsMenuForOne(@PathVariable int restaurantId) {
        return MenuUtil.getAll(menuService.getPopularsMenuForOne(restaurantId));
    }


}
