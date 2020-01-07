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

    @GetMapping("/today")
    public List<RestrntFullInfo> getAllToday() {
        return MenuUtil.getAll(menuService.getTodayMenus());
    }

    @GetMapping("/history/{restaurantId}")
    public List<RestrntFullInfo> getHistoryForOne(@PathVariable int restaurantId) {
        return MenuUtil.getAllForOne(menuService.getHistoryForOne(restaurantId));
    }

    @GetMapping("/history")
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
