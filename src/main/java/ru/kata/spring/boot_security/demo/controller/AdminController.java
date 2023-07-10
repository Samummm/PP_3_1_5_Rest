package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers () {
        return "admin";
    }

//    @GetMapping("/")
//    public String showAllUsers (ModelMap model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "showUsers";
//    }

//    @GetMapping("/info")
//    public String addUser(ModelMap model) {
//        model.addAttribute("user", new User());
//        return "userInfo";
//    }
//
//    @PostMapping("/save")
//    public String saveUser(@ModelAttribute("user") User user, @RequestParam(value = "id", defaultValue = "0") Integer id) {
//        if (id == 0) {
//            userService.saveUser(user);
//        } else {
//            user.setId(id);
//            userService.saveUser(user);
//        }
//        return "redirect:/user/";
//    }
//
//    @RequestMapping(value = "/update")
//    public String updateUser(@RequestParam(value = "id") Integer id, ModelMap model) {
//        model.addAttribute("user", userService.getUser(id));
//        return "userInfo";
//    }
//
////    @ResponseBody
//    @RequestMapping(value ="/delete")
//    public String deleteUser(@RequestParam(value = "id") Integer id) {
//        userService.deleteUser(id);
//        return "redirect:/user/";
//    }

}
