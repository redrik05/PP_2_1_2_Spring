package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    void cleanUsers();

    User getUserByCar(String model, int series);
}