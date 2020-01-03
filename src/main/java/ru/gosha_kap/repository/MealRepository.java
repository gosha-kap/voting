package ru.gosha_kap.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gosha_kap.model.Meal;
import ru.gosha_kap.model.Menu;


@Repository
public interface MealRepository extends JpaRepository<Meal,Integer> {

}
