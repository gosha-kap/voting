package ru.gosha_kap.to;

import java.util.List;

public class RestrntFullInfo {

    private  Integer id;

    private  String name;

    private  int voted;

    private  List<MealTo> menu;

    public RestrntFullInfo(Integer id, String name,   int voted, List<MealTo> meals) {
        this.id = id;
        this.name = name;
        this.voted = voted;
        this.menu = meals;
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public int getVoted() {
        return voted;
    }

    public List<MealTo> getMenu() {
        return menu;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVoted(int voted) {
        this.voted = voted;
    }

    public void setMenu(List<MealTo> meals) {
        this.menu = meals;
    }
}
