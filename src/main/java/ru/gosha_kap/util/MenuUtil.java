package ru.gosha_kap.util;

import ru.gosha_kap.model.Menu;
import ru.gosha_kap.to.MealTo;
import ru.gosha_kap.to.RestrntFullInfo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MenuUtil {
    private MenuUtil() {
    }


    public static List<RestrntFullInfo> getAll(Collection<Menu> menus) {
        return menus.stream().map(menu -> createRestaurantInfo(menu)).
                sorted(Comparator.comparing(RestrntFullInfo::getVoted)).
                collect(Collectors.toList());
    }

    private static RestrntFullInfo createRestaurantInfo(Menu menu) {
        return new RestrntFullInfo(menu.getRestaurant().getId(), menu.getRestaurant().getName(),menu.getRating(),
                menu.getMeals().stream().map(meal -> new MealTo(meal.getDescription(), meal.getPrice())).
                        collect(Collectors.toList()));
    }


}
