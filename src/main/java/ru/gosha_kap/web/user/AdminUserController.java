package ru.gosha_kap.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gosha_kap.model.Role;
import ru.gosha_kap.model.User;
import ru.gosha_kap.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value ="/rest/admin/users",produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController  {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAll() {
        log.info("getAllUsers");
        return userService.getAll();
   }

    @GetMapping("{id}")
    public User getOne(@PathVariable int  id){
        log.info("get {}", id);
        return userService.get(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        userService.delete(id);
    }


    @PatchMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        userService.enable(id, enabled);
    }


    @PutMapping(value = "{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable int id,@RequestBody User user) {
        log.info("update {} with id={}", user, id);
        userService.update(user,id);
        }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody User user) {
        user.addRole(Role.ROLE_ADMIN);
        log.info("create from to {}", user);
        User created = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/profile/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}

