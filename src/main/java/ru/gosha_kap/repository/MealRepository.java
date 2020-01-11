package ru.gosha_kap.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gosha_kap.model.Meal;


@Repository
public interface MealRepository extends JpaRepository<Meal,Integer> {

    @EntityGraph(attributePaths = {"menu"})
    @Query("select m from Meal m where  m.id=?1 and m.menu.restaurant.id=?2")
    Meal getByID(int id, int restaurantID);

}
