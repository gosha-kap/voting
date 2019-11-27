package ru.gosha_kap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gosha_kap.model.Menu;
import ru.gosha_kap.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {


    private final int NOT_NULL_VOTECOUNT = 0;


    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> getTodayMenus() {
        return menuRepository.findAllByDateIsLike(LocalDate.now());
    }

    @Override
    public List<Menu> getHistoryForOne(int id) {
        return menuRepository.findAllByRestaurantIdOrderByDateDesc(id);
    }


    @Override
    @Cacheable("history")
    public List<Menu> getHistoryVoting() {
        LocalDate exceptTodayDate = LocalDate.now();
        return menuRepository.getHistoryVoting(exceptTodayDate);
    }

    @Override
    public List<Menu> getPopularsMenuForOne(int id) {
        return menuRepository.findTop10ByRestaurantIdAndVotesIsGreaterThanOrderByVotesDescDateDesc(id, NOT_NULL_VOTECOUNT);
    }

    @Override
    public List<Menu> getPopularsMenus() {
        return menuRepository.findTop10ByVotesGreaterThanOrderByVotesDescDateDesc(NOT_NULL_VOTECOUNT);
    }


}
