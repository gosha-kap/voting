package ru.gosha_kap.to;

import java.time.LocalDate;

public class VoteTO {
    private int restaurant_id;
    private LocalDate date;

    public VoteTO() {
    }

    public VoteTO(int restaurant_id, LocalDate date) {
        this.restaurant_id = restaurant_id;
        this.date = date;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
