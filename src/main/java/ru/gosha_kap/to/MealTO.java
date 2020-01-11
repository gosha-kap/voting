package ru.gosha_kap.to;

public class MealTO {
    private int id;
    private String description;
    private int price;
    private int menu_id;
    private int restaurant_id;

    public MealTO() {
    }

    public MealTO(int id, String description, int price, int menu_id, int restaurant_id) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.menu_id = menu_id;
        this.restaurant_id = restaurant_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
