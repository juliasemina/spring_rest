package com.example.springrest.controller;

import com.example.springrest.entity.Role;
import com.example.springrest.entity.User;
import com.example.springrest.service.RoleService;
import com.example.springrest.service.UserService;
import com.example.springrest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping("/login_error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/login";
    }

    @GetMapping("/")
    public String index() {
        return "users/index";
    }

    @GetMapping("/admin-panel")
    public String adminPanel() {
        return "users/admin-panel";
    }


}
