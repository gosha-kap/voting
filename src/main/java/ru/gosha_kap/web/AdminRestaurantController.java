package ru.gosha_kap.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gosha_kap.model.Meal;
import ru.gosha_kap.model.Menu;
import ru.gosha_kap.model.Restaurant;
import ru.gosha_kap.service.MenuService;
import ru.gosha_kap.service.RestaurantService;
import ru.gosha_kap.to.MealTO;
import ru.gosha_kap.to.RestrntFullInfo;
import ru.gosha_kap.to.RestrntTO;
import ru.gosha_kap.util.MenuUtil;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value ="/rest/admin/restaurants",produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController {

    protected final Logger log = LoggerFactory.getLogger(getClass());


    private final RestaurantService restaurantService;

    private final MenuService menuService;

    public AdminRestaurantController(RestaurantService restaurantService, MenuService menuService) {
        this.restaurantService = restaurantService;
        this.menuService = menuService;
    }

    @GetMapping
    public List<RestrntTO> getAll() {
        log.info("get all restaurants");
        List<Menu> todaysMenus = menuService.getTodayMenus();
        List<Restaurant> restaurantLIst = restaurantService.getAll();
        return MenuUtil.checkTodayMenus(todaysMenus, restaurantLIst);
    }

    @GetMapping(value = "{restaurantID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public RestrntTO getShortInfo(@PathVariable int restaurantID) {
        log.info("get  restaurants with id =" + restaurantID);
        boolean updated = Objects.nonNull(menuService.getTodayMenu(restaurantID));
        return MenuUtil.createRestaurantTO(restaurantService.get(restaurantID), updated);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<RestrntTO> createRestaurant(@RequestBody Restaurant restaurant) {
        log.info("create new restaurant");
        RestrntTO restaurantCreated = MenuUtil.createRestaurantTO(restaurantService.create(restaurant), false);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/restaurants/{id}")
                .buildAndExpand(restaurantCreated.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(restaurantCreated);
    }

    @PutMapping(value = "{restaurantID}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRestaurant(@PathVariable int restaurantID, @RequestBody Restaurant restaurant ){
        log.info("udpate  restaurant with id="+restaurantID);
        restaurantService.update(restaurant,restaurantID);
    }

    @DeleteMapping("{restaurantID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable int restaurantID){
        log.info("delete  restaurant with id="+restaurantID);
        restaurantService.delete(restaurantID);
    }

    @GetMapping(value = {"{restaurantID}/meals"},consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestrntFullInfo getMeals(@PathVariable int restaurantID) {
        log.info("get  info with restaurantID="+restaurantID);
        return MenuUtil.createRestaurantInfo(getTodayMenu(restaurantID));
    }

    @GetMapping(value = {"{restaurantID}/meals/{mealID}"})
    public MealTO getMeal(@PathVariable int restaurantID, @PathVariable int mealID) {
        log.info("get  meal info with mealID=" + mealID + " and restaurandID = " + restaurantID);
        return MenuUtil.createMealTO(menuService.getMeal(restaurantID, mealID));

    }

    @PostMapping(value = "{restaurantID}/meals",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MealTO createMeal(@PathVariable int restaurantID, @RequestBody Meal meal) {
        log.info("create meal for menu  with restaurantID="+restaurantID);
        return MenuUtil.createMealTO(menuService.createMeal(meal, getTodayMenu(restaurantID)));

    }


    @PutMapping(value = "{restaurantID}/meals/{mealID}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMeal(@PathVariable int restaurantID ,
                           @PathVariable int mealID,
                           @RequestBody Meal meal){
        log.info("update meal for menu  with restaurantID="+restaurantID);
        menuService.update(meal,mealID, getTodayMenu(restaurantID));
       }


    @DeleteMapping(value = "{restaurantID}/meals/{mealID}")
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
