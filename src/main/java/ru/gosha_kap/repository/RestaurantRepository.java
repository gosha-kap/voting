package ru.gosha_kap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gosha_kap.model.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {




    @Query("select r.id,r.name from Restaurant r")
    List<Restaurant> getRestaurants();

    @Query("select r from Restaurant r where r.id=?1")
    Restaurant getRestaurant(int id);

    @Query("select r.timezone from Restaurant r where r.id=?1")
    String getTZ(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);


}
