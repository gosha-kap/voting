package ru.gosha_kap.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.gosha_kap.model.User;
import ru.gosha_kap.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gosha_kap.TestData.USER1;
import static ru.gosha_kap.TestData.USER_MATCHERS;
import static ru.gosha_kap.TestUtil.readFromJson;

class UserControllerTest extends AbstractTestController {


    @Autowired
    private UserService userService;

    public UserControllerTest() {
        super("/rest/profile/");
    }

    @Test
    void getUnAuth() throws Exception {
        perform(doGet())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void get() throws Exception {
        perform(doGet().basicAuth(USER1))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHERS.contentJson(USER1));
    }

    @Test
    void register() throws Exception {
        User newUser = new User("gosha", "pass", "Georgiy", "Efimenko");

        String Json = "{\"login\":\"gosha\",\"pass\":\"pass\",\"name\":\"Georgiy\",\"surName\":\"Efimenko\"}";
        ResultActions action = perform(doPost("/register").readyJsonBody(Json))
                .andDo(print())
                .andExpect(status().isCreated());
          User created = readFromJson(action, User.class);
          Integer newId = created.getId();
          newUser.   setId(newId);
          USER_MATCHERS.assertMatch(created, newUser);
          USER_MATCHERS.assertMatch(userService.get(newId), newUser);
    }

    @Test
    void update() throws Exception {
        String Json = "{\"name\":\"Georgiy\"}";
        perform(doPut().readyJsonBody(Json).basicAuth(USER1))
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(userService.get(1).getName(),"Georgiy");
    }

    @Test
    void updateANotherUser() throws Exception {
        String Json = "{\"id\":\"2\",\"name\":\"Georgiy\"}";
        perform(doPut().readyJsonBody(Json).basicAuth(USER1))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void voteNotExistRestaurant() throws Exception {
        perform(doGetwithParam("vote","id","30").basicAuth(USER1))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void vote() throws Exception {
        perform(doGetwithParam("vote","id","2").basicAuth(USER1))
                .andDo(print())
                .andExpect(status().isAccepted());

    }

}