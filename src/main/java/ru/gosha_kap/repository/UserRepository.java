package ru.gosha_kap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gosha_kap.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
