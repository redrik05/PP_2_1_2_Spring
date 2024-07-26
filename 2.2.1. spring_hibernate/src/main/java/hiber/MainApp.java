package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.cleanUsers();
      Car car2 = new Car("UAZ", 102);
      User user2 = new User("Ilon", "Musk", "spaceshit@gmaol.com", car2);

      User user = new User("John", "Smith", "smith05@mail.ru");
      Car car = new Car("Gaz", 2111);
      user.setCar(car);


      User user1 = new User("Peter", "Griffin", "peter05@mail.ru");
      Car car1 = new Car("Lada", 2110);
      user1.setCar(car1);

      userService.add(user);
      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User us : users) {
         System.out.println("Id = "+us.getId());
         System.out.println("First Name = "+us.getFirstName());
         System.out.println("Last Name = "+us.getLastName());
         System.out.println("Email = "+us.getEmail());
         if (us.getCar()!=null) {
            System.out.println("Car = "+us.getCar().getModel());
            System.out.println("User_Id = "+us.getCar().getUser().getId());
         }
         System.out.println();
      }

      System.out.println("ПОЛУЧАЕМ ПОЛЬЗОВАТЕЛЯ ПО МОДЕЛИ И СЕРИИ АВТО");
      System.out.println(userService.getUserByCar("Lada", 2110).toString());

      context.close();
   }
}
