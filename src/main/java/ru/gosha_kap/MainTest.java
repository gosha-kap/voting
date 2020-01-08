package ru.gosha_kap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.gosha_kap.service.MenuService;
import ru.gosha_kap.util.MenuUtil;


public class MainTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-db.xml");
       /* RestaurantService service =(RestaurantService) context.getBean(RestaurantService.class);
        service.getAll().stream().forEach(System.out::println);*/
        MenuService service2 =(MenuService) context.getBean(MenuService.class);
        MenuUtil.getVoteHistory(service2.getHistoryVoting()).forEach(System.out::println);

    }
}