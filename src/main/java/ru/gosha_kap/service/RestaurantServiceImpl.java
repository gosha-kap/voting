package ru.gosha_kap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gosha_kap.model.Restaurant;
import ru.gosha_kap.repository.RestaurantRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements  RestaurantService {


    @Autowired
    private RestaurantRepository repository;


    @Override
    @Cacheable("menuCache")
    public List<Restaurant> getAll() {
        return  repository.findAll();
    }


}
