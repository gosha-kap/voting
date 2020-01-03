package ru.gosha_kap.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    List<Menu> findAllByRestaurantId(Integer id);

    @EntityGraph(attributePaths = {"restaurant", "meals"})
    List<Menu> findTop10ByRestaurantIdAndVotesIsGreaterThan(int id, int i);

    @EntityGraph(attributePaths = {"restaurant", "meals"})
    List<Menu> findTop10ByVotesGreaterThan(int i);

    @EntityGraph(attributePaths = {"restaurant", "meals"})
    @Query("select m from Menu m where m.votes>0 and m.date<>?1")
    List<Menu> getHistoryVoting(LocalDate localDate);

    @EntityGraph(attributePaths = {"restaurant", "meals"})
    @Query("select m from Menu m where m.restaurant.id=?2 and m.date=?1")
    Menu getTodayInfo(LocalDate date, int id);

    @Modifying
    @Query("delete from Menu m where m.restaurant.id=?1 and m.date=?2")
    void deleteTodayMenu(int id,LocalDate date);


    Menu findByRestaurantIdAndDateIsLike(int id, LocalDate date);

    @Modifying
    @EntityGraph(attributePaths = {"restaurant", "meals"})
    @Query("update  Menu m set m.votes = ?2 where  m.restaurant.id = ?1 and m.date =?3 ")
    void updateVote(int resataurant_id,int rating, LocalDate date);
}
