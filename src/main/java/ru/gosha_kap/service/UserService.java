package ru.gosha_kap.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.gosha_kap.AuthorizedUser;
import ru.gosha_kap.model.User;
import ru.gosha_kap.repository.UserRepository;
import ru.gosha_kap.util.UserUtil;

import java.util.List;

import static ru.gosha_kap.util.ValidationUtil.*;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(readOnly = true)
public class UserService implements UserDetailsService{
    private static final Sort SORT_BY_DATE = Sort.by(Sort.Direction.ASC, "registered", "login");


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private RestaurantService restaurantService;


    public List<User> getAll() {
        return userRepository.findAll(SORT_BY_DATE);
    }

    public User getByLogin(String login){
        Assert.notNull(login, "login must not be null");
        return checkNotFound(userRepository.getUsersByLogin(login), "email=" + login);
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.getUsersByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " is not found");
        }
        return new AuthorizedUser(user);
     }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.getById(id), id);
    }

    @Transactional
    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id)!=0, id);
    }

    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
    }

    @Transactional
    public void update(User user,int id) {
        assureIdConsistent(user, id);
        userRepository.save(UserUtil.prepareToUpDate(get(id),user,passwordEncoder));
    }

    @Transactional
    public User create(User user) {
        checkNew(user);
        return userRepository.save(UserUtil.prepareToSave(user,passwordEncoder));
    }




}
