package ru.gosha_kap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.gosha_kap.service.MenuService;
import ru.gosha_kap.service.UserService;
import ru.gosha_kap.util.MenuUtil;


public class MainTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-db.xml","spring/spring-security.xml","spring/spring-app.xml");
       /* RestaurantService service =(RestaurantService) context.getBean(RestaurantService.class);
        service.getAll().stream().forEach(System.out::println);*/
        UserService service2 = (UserService) context.getBean(UserService.class);
        service2.getAll().stream().forEach(System.out::println);

    }
}
