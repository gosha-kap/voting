package ru.gosha_kap.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gosha_kap.model.User;
import ru.gosha_kap.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService{
    private static final Sort SORT_BY_DATE = Sort.by(Sort.Direction.ASC, "registered", "login");


    @Autowired
    private UserRepository userRepository;


    public List<User> getAll() {

        return userRepository.findAll(SORT_BY_DATE);
    }


}
