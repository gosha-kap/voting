package ru.gosha_kap.to;

public class MealTo {

    private String dish;
    private int prise;

    public MealTo(String dish, int prise) {
        this.dish = dish;
        this.prise = prise;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getPrise() {
        return prise;
    }

    public void setPrise(int prise) {
        this.prise = prise;
    }
}
