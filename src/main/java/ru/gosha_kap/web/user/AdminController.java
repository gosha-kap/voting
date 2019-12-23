package ru.gosha_kap.web.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gosha_kap.model.User;

import java.util.List;

@RestController
@RequestMapping("/rest/admin/users")
public class AdminController extends AbstractUserController {

    @GetMapping
    public List<User> getAll() {
        return super.getAll();
    }
}
