package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/main")
public class MainController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String showAllUsers (Principal principal, ModelMap model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("userLog", user);
        model.addAttribute("roles", user.getRoles());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userNew", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "main";
    }

    @PostMapping("/saveNew")
    public String saveNewUser(@ModelAttribute("user") User user) {
        user.setPassword(userService.encoder(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/main/";
    }

    @GetMapping(value ="/delete")
    public String deleteUser(@RequestParam(value = "id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/main/";
    }
}
