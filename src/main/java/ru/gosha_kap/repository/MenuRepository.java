package ru.gosha_kap.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gosha_kap.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {


    @EntityGraph(attributePaths = {"restaurant","meals"})
    List<Menu> findAllByDateIsLike(LocalDate date);

    @EntityGraph(attributePaths = {"restaurant", "meals"})
    List<Menu> findAllByRestaurantIdOrderByDateDesc(Integer id);

    @EntityGraph(attributePaths = {"restaurant", "meals"})
    List<Menu> findTop10ByRestaurantIdAndVotesIsGreaterThanOrderByVotesDescDateDesc(int id, int i);

    @EntityGraph(attributePaths = {"restaurant", "meals"})
    List<Menu> findTop10ByVotesGreaterThanOrderByVotesDescDateDesc(int i);

    @EntityGraph(attributePaths = {"restaurant", "meals"})
    @Query("select m from Menu m where m.votes>0 and m.date<>?1")
    List<Menu> getHistoryVoting(LocalDate localDate);



}
