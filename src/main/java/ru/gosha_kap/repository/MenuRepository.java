package ru.gosha_kap.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gosha_kap.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {


    @EntityGraph(attributePaths = {"restaurant","meals"})
    List<Menu> findAllByDateIsLike(LocalDate date);
}
