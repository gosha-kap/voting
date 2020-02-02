package ru.gosha_kap;


import org.springframework.test.web.servlet.ResultMatcher;
import ru.gosha_kap.model.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gosha_kap.TestUtil.readListFromJsonMvcResult;

public class TestData {

    public static  Date date1 = new Date();
    {

        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2015-05-30 10:00:00");
        } catch (Exception e) {}
    }

    public static final User USER1 = new User(1, "user1", "pass", "name1", "surname1",true, date1, Role.ROLE_USER);
    public static final User ADMIN = new User( "admin", "111", "name5", "surname5");


    public static final Meal MEAL_1 = new Meal(1, "Картошка", 500);
    public static final Meal MEAL_2 = new Meal(2, "Суп", 1000);
    public static final Meal MEAL_3 = new Meal(3, "Десерт", 700);
    public static final Meal MEAL_4 = new Meal(4, "Лапша", 502);
    public static final Meal MEAL_5 = new Meal(5, "Печень", 1002);
    public static final Meal MEAL_6 = new Meal(6, "Блины", 702);
    public static final Meal MEAL_7 = new Meal(7, "Лаваш", 502);
    public static final Meal MEAL_8 = new Meal(8, "Курица", 1002);
    public static final Meal MEAL_9 = new Meal(9, "Рис", 702);
    public static final Meal MEAL_10 = new Meal(10, "Булка", 502);
    public static final Meal MEAL_11 = new Meal(11, "Пирожок", 1002);
    public static final Meal MEAL_12 = new Meal(12, "Повидло", 702);

    public static final Meal MEAL_13 = new Meal(13, "Пюрешка", 500);
    public static final Meal MEAL_14 = new Meal(14, "Яица", 1000);
    public static final Meal MEAL_15 = new Meal(15, "Кофе", 700);
    public static final Meal MEAL_16 = new Meal(16, "Суп", 502);
    public static final Meal MEAL_17 = new Meal(17, "Лаваш", 1002);
    public static final Meal MEAL_18 = new Meal(18, "Гречка", 702);
    public static final Meal MEAL_19 = new Meal(19, "Борщ", 502);
    public static final Meal MEAL_20 = new Meal(20, "Макароны", 1002);
    public static final Meal MEAL_21 = new Meal(21, "Водка", 702);
    public static final Meal MEAL_22 = new Meal(22, "Рыба", 502);
    public static final Meal MEAL_23 = new Meal(23, "Икра", 1002);
    public static final Meal MEAL_24 = new Meal(24, "Пиво", 702);


    public static final Meal MEAL_25 = new Meal(25, "Каша", 500);
    public static final Meal MEAL_26 = new Meal(26, "Маша", 1000);
    public static final Meal MEAL_27 = new Meal(27, "Грибы", 700);
    public static final Meal MEAL_28 = new Meal(28, "Тунец", 502);
    public static final Meal MEAL_29 = new Meal(29, "Мармелад", 1002);
    public static final Meal MEAL_30 = new Meal(30, "Индейка", 702);
    public static final Meal MEAL_31 = new Meal(31, "Ягода", 502);
    public static final Meal MEAL_32 = new Meal(32, "Горбуша", 1002);
    public static final Meal MEAL_33 = new Meal(33, "Нут", 702);
    public static final Meal MEAL_34 = new Meal(34, "Бублик", 502);
    public static final Meal MEAL_35 = new Meal(35, "Сосиски", 1002);
    public static final Meal MEAL_36 = new Meal(36, "Утка", 702);

    public static final Meal MEAL_37 = new Meal(37, "Биг-Мак", 500);
    public static final Meal MEAL_38 = new Meal(38, "Кола", 1000);
    public static final Meal MEAL_39 = new Meal(39, "Фри", 700);
    public static final Meal MEAL_40 = new Meal(40, "Суп", 502);
    public static final Meal MEAL_41 = new Meal(41, "Креветка", 1002);
    public static final Meal MEAL_42 = new Meal(42, "Пиво", 702);
    public static final Meal MEAL_43 = new Meal(43, "Лаваш", 502);
    public static final Meal MEAL_44 = new Meal(44, "Брутуч", 1002);
    public static final Meal MEAL_45 = new Meal(45, "Пенце", 702);

    public static final Restaurant RESTAURANT_1 = new Restaurant(1, "Ashan");
    public static final Restaurant RESTAURANT_2 = new Restaurant(2, "Mishan");
    public static final Restaurant RESTAURANT_3 = new Restaurant(3, "Ravshan");
    public static final Restaurant RESTAURANT_4 = new Restaurant(4, "Goshan");

    public static final Menu MENU_1 = new Menu(1, LocalDate.parse("2015-05-29"), 4, RESTAURANT_1, List.of(MEAL_1, MEAL_2, MEAL_3));
    public static final Menu MENU_2 = new Menu(2, LocalDate.parse("2015-05-29"), 1, RESTAURANT_2, List.of(MEAL_4, MEAL_5, MEAL_6));
    public static final Menu MENU_3 = new Menu(3, LocalDate.parse("2015-05-29"), 0, RESTAURANT_3, List.of(MEAL_7, MEAL_8, MEAL_9));
    public static final Menu MENU_4 = new Menu(4, LocalDate.parse("2015-05-29"), 0, RESTAURANT_4, List.of(MEAL_10, MEAL_11, MEAL_12));

    public static final Menu MENU_5 = new Menu(5, LocalDate.parse("2017-08-29"), 3, RESTAURANT_1, List.of(MEAL_13, MEAL_14, MEAL_15));
    public static final Menu MENU_6 = new Menu(6, LocalDate.parse("2017-08-29"), 2, RESTAURANT_2, List.of(MEAL_16, MEAL_17, MEAL_18));
    public static final Menu MENU_7 = new Menu(7, LocalDate.parse("2017-08-29"), 0, RESTAURANT_3, List.of(MEAL_19, MEAL_20, MEAL_21));
    public static final Menu MENU_8 = new Menu(8, LocalDate.parse("2017-08-29"), 0, RESTAURANT_4, List.of(MEAL_22, MEAL_23, MEAL_24));

    public static final Menu MENU_9 = new Menu(9, LocalDate.parse("2018-08-29"), 1, RESTAURANT_1, List.of(MEAL_25, MEAL_26, MEAL_27));
    public static final Menu MENU_10 = new Menu(10, LocalDate.parse("2018-08-29"), 1, RESTAURANT_2, List.of(MEAL_28, MEAL_29, MEAL_30));
    public static final Menu MENU_11 = new Menu(11, LocalDate.parse("2018-08-29"), 2, RESTAURANT_3, List.of(MEAL_31, MEAL_32, MEAL_33));
    public static final Menu MENU_12 = new Menu(12, LocalDate.parse("2018-08-29"), 1, RESTAURANT_4, List.of(MEAL_34, MEAL_35, MEAL_36));

    public static final Menu MENU_13 = new Menu(13, LocalDate.now(), 1, RESTAURANT_1, List.of(MEAL_37, MEAL_38, MEAL_39));
    public static final Menu MENU_14 = new Menu(14, LocalDate.now(), 0, RESTAURANT_2, List.of(MEAL_40, MEAL_41, MEAL_42));
    public static final Menu MENU_15 = new Menu(15, LocalDate.now(), 2, RESTAURANT_3, List.of(MEAL_43, MEAL_44, MEAL_45));

    public static final List<Menu> MENUS_HISTORY = List.of(MENU_1, MENU_2, MENU_3, MENU_4, MENU_5, MENU_6,
            MENU_7, MENU_8, MENU_9, MENU_10, MENU_11, MENU_12).stream().
            filter(menu -> menu.getVotes() > 0).collect(Collectors.toList());

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringNullFields(expected);
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparator(Comparator.comparing(Menu::getId)).isEqualTo(expected);
    }

    public static <T> ResultMatcher contentJson(T... expected) {
        return contentJson(List.of(expected));
    }

    public static <T> ResultMatcher contentJson(Iterable<T> expected, Class<T> claz) {
        return result -> {
            assertThat(readListFromJsonMvcResult(result, claz)).isEqualTo(expected);
        };
    }

    public static TestMatchers<User> USER_MATCHERS = TestMatchers.useFieldsComparator(User.class, "registered", "pass","roles");


}
