package ru.gosha_kap.util.json;

import org.junit.jupiter.api.Test;
import ru.gosha_kap.model.Menu;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.gosha_kap.TestData.*;


class JsonUtilTest {

    @Test
    void readWriteValue() throws Exception {
        String json = JsonUtil.writeValue(MENU_1);
        System.out.println(json);
        Menu menu = JsonUtil.readValue(json, Menu.class);
        assertMatch(menu, MENU_1);
    }

    @Test
    void readWriteValues() throws Exception {
        List<Menu> menusBefore = List.of(MENU_12, MENU_5);
        String json = JsonUtil.writeValue(menusBefore);
        System.out.println(json);
        List<Menu> menusAfter = JsonUtil.readValues(json, Menu.class);
        assertMatch(menusAfter, menusBefore);
    }


}

