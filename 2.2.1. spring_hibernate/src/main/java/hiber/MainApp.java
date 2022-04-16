package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Denis","Ilinsky","vaz@mail.ru");
      User user2 = new User("Viktoriy","Ilinskay","gaz@mail.ru");
      User user3 = new User("Diana","Ilinskay","uaz@mail.ru");
      User user4 = new User("Sergey","Sergeevich","kamaz@mail.ru");

      Car vaz = new Car("2121",111);
      Car gaz = new Car("3105",222);
      Car uaz = new Car("469",333);
      Car kamaz = new Car("55102",444);

      userService.add(user1.setCar(vaz).setUser(user1));
      userService.add(user2.setCar(gaz).setUser(user2));
      userService.add(user3.setCar(uaz).setUser(user3));
      userService.add(user4.setCar(kamaz).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("vaz", 111));

      try {
         User notFoundUser = userService.getUserByCar("gaz", 222);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
