package ru.gosha_kap.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gosha_kap.model.Role;
import ru.gosha_kap.model.User;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value ="/rest/admin",produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController extends AbstractUserController {


    @GetMapping("/users")
    public List<User> getAll() {
        return super.getAll();
    }


    @DeleteMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }


    @PatchMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        super.enable(id, enabled);
    }


    @PutMapping(value = "/users/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updatePass(@PathVariable int id,@RequestBody User user) {
        super.update(user,id);
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody User user) {
        User created = super.create(user, Role.ROLE_ADMIN);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/profile/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}

