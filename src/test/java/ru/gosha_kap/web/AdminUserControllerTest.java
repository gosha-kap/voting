package ru.gosha_kap.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.gosha_kap.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gosha_kap.TestData.ADMIN;
import static ru.gosha_kap.TestData.USER1;

class AdminUserControllerTest extends AbstractTestController {

    private AdminUserControllerTest() {
        super("/rest/admin/users");
    }

    @Autowired
    private UserService userService;

    @Test
    void getAllNotAdmin() throws Exception {
        perform(doGet().basicAuth(USER1))
                .andExpect(status().isForbidden());
    }

    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(ADMIN))
                .andExpect(status().isOk());
    }

    @Test
    void deleteNotExistUser() throws Exception {
        perform(doDelete(30).basicAuth(ADMIN)).
                andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deleteUser() throws Exception {
        perform(doDelete(3).basicAuth(ADMIN)).
                andExpect(status().isNoContent());
        assertEquals(userService.getAll().size(),4);
    }

    @Test
    void disableUser() throws Exception {
        perform(doPutwithParam(1,"enabled","false").basicAuth(ADMIN)).
                andExpect(status().isNoContent());
        assertEquals(userService.get(1).isEnabled(),false);

    }

    @Test
    void updateUser() throws Exception{
        String Json = "{\"id\":\"3\",\"name\":\"Georgiy\"}";
        perform(doPut(3).basicAuth(ADMIN).readyJsonBody(Json)).
                andExpect(status().isNoContent());
        assertEquals(userService.get(3).getName(),"Georgiy");
    }
 }