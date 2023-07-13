package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.EncoderService;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private EncoderService encoderService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, EncoderService encoderService) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoderService = encoderService;
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
        return "userInfo";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(value = "id", defaultValue = "0") Integer id) {
        if (id == 0) {
            user.setPassword(encoderService.encoder(user.getPassword()));
        } else {
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                user.setPassword(userService.getUser(id).getPassword());
            } else {
                user.setPassword(encoderService.encoder(user.getPassword()));
            }
            user.setId(id);
        }

        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/update")
    public String updateUser(@RequestParam(value = "id") Integer id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "userInfo";
    }

    @RequestMapping(value ="/delete")
    public String deleteUser(@RequestParam(value = "id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
