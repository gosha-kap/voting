package ru.gosha_kap.service;

import ru.gosha_kap.model.Meal;
import ru.gosha_kap.model.Menu;
import ru.gosha_kap.model.Restaurant;

import java.util.List;

public interface MenuService {

    List<Menu> getTodayMenus();

    List<Menu> getHistoryForOne(int id);

    List<Menu> getHistoryVoting();

    List<Menu> getPopularsMenuForOne(int id);

    List<Menu> getPopularsMenus();

    Menu getTodayMenu(int id);

    Menu createMenu(Restaurant restaurant);

    void createMeal(Meal meal,Menu menu);

    void update(Meal meal, int mealID, Menu menu);

    void delete(int mealID, Menu todayMenu);

    void delete(int restaurantID);

    void checkTodayMenu(int id);

    void updateVotes();
}
