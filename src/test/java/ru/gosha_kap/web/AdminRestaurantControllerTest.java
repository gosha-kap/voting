package ru.gosha_kap.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import ru.gosha_kap.service.MenuService;
import ru.gosha_kap.service.RestaurantService;
import ru.gosha_kap.to.MealTO;
import ru.gosha_kap.to.RestrntTO;
import ru.gosha_kap.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gosha_kap.TestData.ADMIN;
import static ru.gosha_kap.TestUtil.readFromJson;

class AdminRestaurantControllerTest extends AbstractTestController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;

    private AdminRestaurantControllerTest() {
        super("/rest/admin/restaurants/");
    }

    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(ADMIN))
                .andExpect(status().isOk());
    }

    @Test
    void getShortInfo() throws Exception {
        ResultActions actions = perform(doGet(4).basicAuth(ADMIN)).
                andDo(print()).andExpect(status().isOk());
        RestrntTO restrnt = readFromJson(actions, RestrntTO.class);
        assertEquals(restrnt.getMenuUpdated(), false);
    }

    @Test
    void checkValidate() throws Exception {
        String Json = "{\"name\":\"TestRestaurntName\",\"timezone\":\"GMTerror+10:00\"}";
        ResultActions action = perform(doPost().basicAuth(ADMIN).readyJsonBody(Json))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void createRestaurant() throws Exception {
        String Json = "{\"name\":\"TestRestaurntName\",\"timezone\":\"GMT+10:00\"}";
        ResultActions action = perform(doPost().basicAuth(ADMIN).readyJsonBody(Json))
                .andDo(print())
                .andExpect(status().isCreated());
        assertEquals(restaurantService.getAll().size(), 5);
    }

    @Test
    void updateRestaurantInvalidDate() throws Exception {
        String Json = "{\"name\":\"TestRestaurntName\",\"id\":\"1\"}";
        perform(doPut(2).basicAuth(ADMIN).readyJsonBody(Json)).andDo(print()).
                andExpect(status().isUnprocessableEntity());
    }

    @Test
    void updateRestaurant() throws Exception {
        String Json = "{\"name\":\"TestName\",\"id\":\"1\"}";
        perform(doPut(1).basicAuth(ADMIN).readyJsonBody(Json)).andDo(print()).
                andExpect(status().isNoContent());
        assertEquals(restaurantService.get(1).getName(), "TestName");
    }

    @Test
    void deleteRestaurantNoExist() throws Exception {
        perform((doDelete(40)).basicAuth(ADMIN)).andDo(print()).
                andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deleteRestaurant() throws Exception {
        perform((doDelete(3)).basicAuth(ADMIN)).andDo(print()).
                andExpect(status().isNoContent());
        assertEquals(restaurantService.getAll().size(), 3);
    }

    @Test
    void getMeals() throws Exception {
        assertThrows(NotFoundException.class,
                () -> menuService.checkTodayMenu(4));
        perform(doGet("4/meals").basicAuth(ADMIN))
                .andExpect(status().isOk());
        menuService.checkTodayMenu(4);
    }

    @Test
    void createMeal() throws Exception {
        String Json = "{\"description\":\"testmeal1\",\"price\":\"1000\"}";
        ResultActions actions = perform(doPost("4/meals").basicAuth(ADMIN).readyJsonBody(Json)).andDo(print()).
                andExpect(status().isCreated());
        MealTO mealTO = readFromJson(actions, MealTO.class);
        assertEquals(mealTO.getRestaurant_id(), 4);

    }

}