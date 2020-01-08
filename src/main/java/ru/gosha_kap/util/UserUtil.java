package ru.gosha_kap.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.gosha_kap.model.User;

import static org.springframework.util.StringUtils.hasText;

public class UserUtil {


    public static User prepareToUpDate(User original, User user, PasswordEncoder passwordEncoder) {

        if(hasText(user.getName()))
            original.setName(user.getName());
        if(hasText(user.getSurName()))
            original.setSurName(user.getSurName());
        if(hasText(user.getPass())){
            String password = user.getPass();
            original.setPass(passwordEncoder.encode(password));
        }
          return original;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPass();
        user.setPass(hasText(password) ? passwordEncoder.encode(password) : password);
        return user;
    }
}
