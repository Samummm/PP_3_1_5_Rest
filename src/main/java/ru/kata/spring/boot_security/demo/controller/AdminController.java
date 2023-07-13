package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

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


    @GetMapping("/")
    public String showAllUsers (ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/info")
    public String addUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "userNew";
    }

    @PostMapping("/saveNew")
    public String saveNewUser(@ModelAttribute("user") User user) {
        user.setPassword(userService.encoder(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @PostMapping("/saveOld")
    public String saveOldUser(@ModelAttribute("user") User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(userService.getUser(user.getId()).getPassword());
        } else {
            user.setPassword(userService.encoder(user.getPassword()));
        }
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping(value = "/update")
    public String updateUser(@RequestParam(value = "id") Integer id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "userOld";
    }

    @GetMapping(value ="/delete")
    public String deleteUser(@RequestParam(value = "id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
