package ru.gosha_kap.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gosha_kap.model.Meal;
import ru.gosha_kap.model.Menu;
import ru.gosha_kap.model.Restaurant;
import ru.gosha_kap.service.MenuService;
import ru.gosha_kap.service.RestaurantService;
import ru.gosha_kap.to.RestrntFullInfo;
import ru.gosha_kap.util.MenuUtil;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value ="/rest/admin/restaurants",produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;


    @GetMapping
    public List<Restaurant> getAll(){
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRestaurant(@RequestBody Restaurant restaurant ){
        log.info("create new restaurant");
        restaurantService.create(restaurant);
    }

    @PutMapping(value = "{restaurantID}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRestaurant(@PathVariable int restaurantID, @RequestBody Restaurant restaurant ){
        log.info("udpate  restaurant with id="+restaurantID);
        restaurantService.update(restaurant,restaurantID);
    }

    @DeleteMapping(value = "{restaurantID}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRestaurant(@PathVariable int restaurantID){
        log.info("delete  restaurant with id="+restaurantID);
        restaurantService.delete(restaurantID);
    }

    @GetMapping(value = {"{restaurantID}","{restaurantID}/meals"},consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestrntFullInfo get(@PathVariable int restaurantID){
        log.info("get  info with restaurantID="+restaurantID);
        return MenuUtil.createRestaurantInfo(getTodayMenu(restaurantID));
    }

    @PostMapping(value = "{restaurantID}/meals",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createMeal(@PathVariable int restaurantID , @RequestBody Meal meal){
        log.info("create meal for menu  with restaurantID="+restaurantID);
        menuService.createMeal(meal,getTodayMenu(restaurantID));
    }

    @PutMapping(value = "{restaurantID}/meals/{mealID}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMeal(@PathVariable int restaurantID ,
                           @PathVariable int mealID,
                           @RequestBody Meal meal){
        log.info("update meal for menu  with restaurantID="+restaurantID);
        menuService.update(meal,mealID, getTodayMenu(restaurantID));
       }

    @DeleteMapping(value = "{restaurantID}/meals/{mealID}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMeal(@PathVariable int restaurantID ,
                           @PathVariable int mealID){
        log.info("delete meal for menu  with restaurantID="+restaurantID);
        menuService.delete(mealID, getTodayMenu(restaurantID));
    }

    @DeleteMapping(value = "{restaurantID}/meals",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMenu(@PathVariable int restaurantID){
        log.info("delete today's menu  for restaurantID="+restaurantID);
        menuService.delete(restaurantID);
    }

    private Menu getTodayMenu(@PathVariable int restaurantID) {
        Restaurant restaurant = restaurantService.get(restaurantID);
        return Objects.isNull(menuService.getTodayMenu(restaurantID)) ? menuService.createMenu(restaurant) :
                        menuService.getTodayMenu(restaurantID);
   }


}
