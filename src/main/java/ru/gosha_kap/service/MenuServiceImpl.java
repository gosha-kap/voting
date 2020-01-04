package ru.gosha_kap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.gosha_kap.model.*;
import ru.gosha_kap.repository.MealRepository;
import ru.gosha_kap.repository.MenuRepository;
import ru.gosha_kap.repository.VoteJPARepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.gosha_kap.util.ValidationUtil.*;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {


    private final int NOT_NULL_VOTECOUNT = 0;


    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private VoteJPARepository voteJPARepository;



    @Override
    public List<Menu> getTodayMenus() {
        return menuRepository.findAllByDateIsLike(LocalDate.now());
    }

    @Override
    public List<Menu> getHistoryForOne(int id) {
        return menuRepository.findAllByRestaurantId(id);
    }


    @Override
    @Cacheable("history")
    public List<Menu> getHistoryVoting() {
        LocalDate exceptTodayDate = LocalDate.now();
        return menuRepository.getHistoryVoting(exceptTodayDate);
    }

    @Override
    public List<Menu> getPopularsMenuForOne(int id) {
        return menuRepository.findTop10ByRestaurantIdAndVotesIsGreaterThan(id, NOT_NULL_VOTECOUNT);
    }

    @Override
    public List<Menu> getPopularsMenus() {
        return menuRepository.findTop10ByVotesGreaterThan(NOT_NULL_VOTECOUNT);
    }

    @Override
    public Menu getTodayMenu(int restaurantId) {
        return menuRepository.getTodayInfo(LocalDate.now(),restaurantId);
    }

    @Override
    @Transactional
    public Menu createMenu(Restaurant restaurant) {

      Menu menu = new Menu(restaurant);
      return menuRepository.save(menu);

    }

    @Override
    @Transactional
    public void createMeal(Meal meal, Menu menu) {
        Assert.notNull(meal, "meal must not be null");
        checkNew(meal);
        meal.setMenu(menu);
        mealRepository.save(meal);
    }

    @Override
    @Transactional
    public void update(Meal meal, int mealID, Menu menu) {
        Assert.notNull(meal, "meal must not be null");
        assureIdConsistent(meal,mealID);
        assureIdIsIncluded(menu,meal);
        meal.setMenu(menu);
        mealRepository.save(meal);
    }

    @Override
    @Transactional
    public void delete(int mealID, Menu menu) {
        Meal meal = mealRepository.getOne(mealID);
        checkNotFound(meal,": error delete meal");
        assureIdIsIncluded(menu,meal);
        mealRepository.delete(meal);
    }

    @Override
    @Transactional
    public void delete(int restaurantID) {
        menuRepository.deleteTodayMenu(restaurantID, LocalDate.now());
    }

    @Override
    public void checkTodayMenu(int id) {
        checkNotFound(menuRepository.findByRestaurantIdAndDateIsLike(id,LocalDate.now()),"No restaurant found or menu is not updated");
    }

    @Override
    @Transactional
    public void updateVotes() {
        List<VoteEntity> votes = voteJPARepository.getTodayResult(LocalDate.now());
        Map<Integer,Integer> result = new HashMap<>();
        votes.stream().forEach(x->{
            result.merge(x.getRestaurant_id(),1,Integer::sum);
        });
        for(Map.Entry<Integer,Integer> val: result.entrySet()){
           menuRepository.updateVote(val.getKey(),val.getValue(),LocalDate.now());
        }

    }


}
