package ru.gosha_kap.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gosha_kap.model.Role;
import ru.gosha_kap.model.User;
import ru.gosha_kap.model.VoteEntity;
import ru.gosha_kap.service.MenuService;
import ru.gosha_kap.service.VoteService;


import java.net.URI;
import java.util.List;

import static ru.gosha_kap.util.SecurityUtil.authUserId;

@RestController
@RequestMapping(value ="/rest/profile",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends AbstractUserController {



    @Autowired
    private MenuService menuService;

    @Autowired
    private VoteService voteService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(authUserId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user) {
        super.update(user, authUserId());
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody User user) {
        User created = super.create(user, Role.ROLE_USER);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/profile/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value="/vote",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> vote(@RequestParam int id){
        menuService.checkTodayMenu(id);
        if(!voteService.checkTime(id))
            return new ResponseEntity<>("Sorry. It's too late to vote",HttpStatus.ACCEPTED);
        voteService.vote(authUserId(),id);
        menuService.updateVotes();
        return new ResponseEntity<>("You  voted  for restaurant with id = "+id,HttpStatus.ACCEPTED);

    }

    @GetMapping(value="/history",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<VoteEntity> getHistoryForUser(){
        return voteService.getHistoryForUser(authUserId());
    }


}
