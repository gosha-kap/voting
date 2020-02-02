package ru.gosha_kap.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.gosha_kap.model.User;
import ru.gosha_kap.model.VoteEntity;
import ru.gosha_kap.to.VoteTO;
import ru.gosha_kap.util.exception.IllegalRequestDataException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

public class UserUtil {

    private UserUtil() {
    }

    public static User prepareToUpDate(User original, User user, PasswordEncoder passwordEncoder) {

        if (hasText(user.getLogin()))
            throw new IllegalRequestDataException("Field \"login\" is immutable");
        if (!user.isEnabled())
            throw new IllegalRequestDataException("Can't disable profile");
        if (user.getRoles().size() > 0)
            throw new IllegalRequestDataException("Can't change user role");
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

    public static List<VoteTO> toVoteTO(List<VoteEntity> historyForUser) {
        return historyForUser.stream().map(UserUtil::createMealTO).sorted(Comparator.comparing(VoteTO::getDate).reversed()).
                collect(Collectors.toList());
    }

    public static VoteTO createMealTO(VoteEntity entity) {
        return new VoteTO(entity.getRestaurant_id(), entity.getVote().getVotedDate());
    }
}
