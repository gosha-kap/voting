package ru.gosha_kap.to;

import ru.gosha_kap.model.Meal;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class RestrntFullInfo {

    private int restaurant_id;

    private String restaurant_name;

    private LocalDate date;

    private int voted;

    private int menu_id;

    private List<Meal> menu;


    public RestrntFullInfo(int restaurant_id, String name, LocalDate date, int voted, int menu_id, List<Meal> meals) {
        this.restaurant_id = restaurant_id;
        this.restaurant_name = name;
        this.voted = voted;
        this.menu = meals;
        this.menu_id = menu_id;
        this.date = date;
    }

    public RestrntFullInfo() {
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public int getVoted() {
        return voted;
    }

    public List<Meal> getMenu() {
        return menu;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public void setVoted(int voted) {
        this.voted = voted;
    }

    public void setMenu(List<Meal> meals) {
        this.menu = meals;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestrntFullInfo that = (RestrntFullInfo) o;
        return restaurant_id == that.restaurant_id &&
                voted == that.voted &&
                menu_id == that.menu_id &&
                restaurant_name.equals(that.restaurant_name) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurant_id, restaurant_name, date, voted, menu_id);
    }


}
