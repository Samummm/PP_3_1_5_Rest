package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.User;
import java.util.List;


public interface UserService {

    public void saveUser(User user);

    public void deleteUser(Integer id);

    public User getUser(Integer id);

    public List<User> getAllUsers();

    public User findByEmail(String email);

    public User encoder(User user);
}
