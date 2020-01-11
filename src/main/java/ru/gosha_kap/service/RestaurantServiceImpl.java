package ru.gosha_kap.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gosha_kap.model.Restaurant;
import ru.gosha_kap.repository.RestaurantRepository;

import java.util.List;

import static ru.gosha_kap.util.MenuUtil.prepareToUpdate;
import static ru.gosha_kap.util.ValidationUtil.*;

@Service
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements  RestaurantService {


    private final RestaurantRepository repository;

    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Restaurant> getAll() {
        return  repository.findAll();
    }

    @Override
    public Restaurant get(int id) {
        return checkNotFound(repository.findById(id).orElse(null),":Detail: No restaurant with id="+id+" found");
    }

    @Override
    @Transactional
    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        checkTimeZone(restaurant.getTimezone());
       return repository.save(restaurant);

    }

    @Override
    @Transactional
    public void update(Restaurant restaurant, int restaurantID) {
        assureIdConsistent(restaurant,restaurantID);
        Restaurant original = repository.getRestaurant(restaurantID);
        repository.save(prepareToUpdate(original,restaurant));
    }

    @Override
    @Transactional
    public void delete(int restaurantID) {
        checkNotFoundWithId(repository.delete(restaurantID) != 0, restaurantID);

    }


}
