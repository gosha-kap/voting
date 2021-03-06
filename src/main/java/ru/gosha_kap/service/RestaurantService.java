package ru.gosha_kap.service;


import ru.gosha_kap.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();

    Restaurant get(int id);

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant, int restaurantID);

    void delete(int restaurantID);
}
