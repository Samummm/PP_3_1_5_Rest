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
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

// --------------------------------------------------------------------------------------

    @GetMapping("/")
    public String showAllUsers (Principal principal, ModelMap model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userNew", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "admin";
    }

    @PostMapping("/save")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(userService.encoder(user));
        return "redirect:/admin/";
    }

    @PostMapping("/update")
//    @ResponseBody
    public String saveOldUser(@ModelAttribute("user") User user) {
        if(user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(userService.getUser(user.getId()).getRoles());
        }
        userService.saveUser(userService.encoder(user));
        return "redirect:/admin/";
    }

    @GetMapping(value ="/delete")
    public String deleteUser(@RequestParam(value = "id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
