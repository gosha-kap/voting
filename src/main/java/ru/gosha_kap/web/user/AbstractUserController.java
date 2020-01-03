package ru.gosha_kap.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gosha_kap.model.Menu;
import ru.gosha_kap.model.Role;
import ru.gosha_kap.model.User;
import ru.gosha_kap.service.MenuService;
import ru.gosha_kap.service.UserService;

import java.util.List;

import static ru.gosha_kap.util.ValidationUtil.assureIdConsistent;


public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @Autowired
    MenuService menuService;


    public List<User> getAll() {
        log.info("getAllUsers");
        return service.getAll();
    }


    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }


    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    protected void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        service.update(user,id);
    }


    protected  User create(User user,Role role){
        user.addRole(role);
        log.info("create from to {}", user);
        return service.create(user);
    }


}
