package com.jumshudivanych.simplecrm.controller;

import com.jumshudivanych.simplecrm.entity.User;
import com.jumshudivanych.simplecrm.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

import static com.jumshudivanych.simplecrm.entity.Role.USER;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null) {
            model.put("servicemessage", "User exists");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(USER));
        userRepo.save(user);
        return "redirect:/home";
    }
}
