package ru.gosha_kap.util;

import ru.gosha_kap.model.Menu;
import ru.gosha_kap.model.Restaurant;
import ru.gosha_kap.to.RestrntFullInfo;
import ru.gosha_kap.to.RestrntVoteHistory;
import ru.gosha_kap.to.RestrntVoteInfo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MenuUtil {
    private MenuUtil() {
    }


    public static List<RestrntFullInfo> getAll(Collection<Menu> menus) {
        return menus.stream().map(MenuUtil::createRestaurantInfo).
                sorted(Comparator.comparing(RestrntFullInfo::getVoted).reversed()
                        .thenComparing(Comparator.comparing(RestrntFullInfo::getRestaurant_name))
                        .thenComparing(Comparator.comparing(RestrntFullInfo::getDate).reversed())).
                collect(Collectors.toList());
    }

    public static List<RestrntFullInfo> getAllForOne(Collection<Menu> menus) {
        return menus.stream().map(MenuUtil::createRestaurantInfo)
                .sorted(Comparator.comparing(RestrntFullInfo::getDate).reversed())
                .collect(Collectors.toList());
    }

    public static List<RestrntVoteHistory> getVoteHistory(Collection<Menu> menus) {
        Map<LocalDate, List<RestrntVoteInfo>> dateListMap = new HashMap<>();
        menus.forEach(menu -> dateListMap.put(menu.getDate(), new ArrayList<>()));
        menus.forEach(menu -> {
            List<RestrntVoteInfo> list = dateListMap.get(menu.getDate());
            list.add(new RestrntVoteInfo(menu.getRestaurant().getId(), menu.getRestaurant().getName(), menu.getVotes(), menu.getId()));
            dateListMap.replace(menu.getDate(), list);
        });
        return dateListMap.entrySet().stream().map(item -> createHistoryItem(item.getKey(), item.getValue())).
                sorted(Comparator.comparing(RestrntVoteHistory::getDate).reversed()).collect(Collectors.toList());
    }

    public static RestrntFullInfo createRestaurantInfo(Menu menu) {
        return new RestrntFullInfo(menu.getRestaurant().getId(),
                menu.getRestaurant().getName(),
                menu.getDate(),
                menu.getVotes(),
                menu.getId(),
                menu.getMeals());
    }

    public static RestrntVoteHistory createHistoryItem(LocalDate date, List<RestrntVoteInfo> list) {
        return new RestrntVoteHistory(date, list.stream().sorted(Comparator.comparing(RestrntVoteInfo::getVoted).reversed()).collect(Collectors.toList()));
    }

    public static Restaurant prepareToUpdate(Restaurant original, Restaurant restaurant){
        original.setName(restaurant.getName());
        return original;
    }

}
