package ru.gosha_kap.service;

import ru.gosha_kap.model.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getTodayMenus();

    List<Menu> getHistoryForOne(int id);

    List<Menu> getHistoryVoting();

    List<Menu> getPopularsMenuForOne(int id);

    List<Menu> getPopularsMenus();
}
