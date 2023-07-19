package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

// --------------------------------------------------------------------------------------

    @GetMapping("/entities")
    public List<User> showAllUsers () {
        List<User> allUser = userService.getAllUsers();
        return allUser;
    }

    @GetMapping("/entities/{id}")
    public User getUser (@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping("/entities")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(userService.encoder(user));
    }

    @PutMapping("/entities")
    public void update(@RequestBody User user) {
        userService.saveUser(userService.encoder(user));
    }

    @DeleteMapping("/entities/{id}")
    public void deleteUser (@PathVariable int id) {
        userService.deleteUser(id);
    }
}
