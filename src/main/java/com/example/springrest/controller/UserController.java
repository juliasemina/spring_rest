package com.example.springrest.controller;

import com.example.springrest.entity.User;
import com.example.springrest.service.UserService;
import com.example.springrest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String currentUser() {
        return "users/user-page";
    }
}
