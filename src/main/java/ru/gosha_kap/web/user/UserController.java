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
import ru.gosha_kap.service.MenuService;
import ru.gosha_kap.service.UserService;
import ru.gosha_kap.service.VoteService;
import ru.gosha_kap.to.VoteTO;
import ru.gosha_kap.util.UserUtil;

import java.net.URI;
import java.util.List;

import static ru.gosha_kap.util.SecurityUtil.authUserId;

@RestController
@RequestMapping(value ="/rest/profile",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController  {


    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        int id = authUserId();
        log.info("get {}", id);
        return userService.get(id);
     }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> update(@RequestBody User user) {
        int id = authUserId();
        log.info("update {} with id={}", user, id);
        return new ResponseEntity<User>(userService.update(user, id), HttpStatus.OK);

    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody User user) {
        user.addRole(Role.ROLE_USER);
        log.info("create from to {}", user);
        User created = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/profile/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);

    }

    @GetMapping(value="/vote",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> vote(@RequestParam int id){
        menuService.checkTodayMenu(id);
        if(voteService.checkTime(id))
            return new ResponseEntity<>("Sorry. It's too late to vote",HttpStatus.ACCEPTED);
        voteService.vote(authUserId(),id);
        menuService.updateVotes();
        return new ResponseEntity<>("You  voted  for restaurant with id = "+id,HttpStatus.ACCEPTED);

    }

    @GetMapping(value="/history")
    public List<VoteTO> getHistoryForUser() {
        return UserUtil.toVoteTO(voteService.getHistoryForUser(authUserId()));
    }


}
