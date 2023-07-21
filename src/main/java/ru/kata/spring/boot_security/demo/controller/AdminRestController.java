package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.exception_handling.AppError;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.ArrayList;
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
        List<User> allUser = new ArrayList<>();
        try {
            allUser = userService.getAllUsers();
        } catch (Exception e) {
            throw e;
        }
        return allUser;
    }

    @GetMapping("/entities/{id}")
    public User getUser (@PathVariable int id) {
        User user = new User();
        try {
            user = userService.getUser(id);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

    @PostMapping("/entities")
    public void saveUser(@RequestBody User user) {
        try {
            userService.saveUser(userService.encoder(user));
        } catch (Exception e) {
        throw e;
        }
    }

    @PutMapping("/entities")
    public void update(@RequestBody User user) {
        try {
            if(user.getRoles() == null || user.getRoles().isEmpty()) {
                user.setRoles(userService.getUser(user.getId()).getRoles());
            }
            userService.saveUser(userService.encoder(user));
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/entities/{id}")
    public void deleteUser (@PathVariable int id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(new AppError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
