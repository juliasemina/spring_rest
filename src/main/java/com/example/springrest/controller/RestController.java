package com.example.springrest.controller;

import com.example.springrest.entity.Role;
import com.example.springrest.entity.User;
import com.example.springrest.service.RoleService;
import com.example.springrest.service.UserService;
import com.example.springrest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
//@RequestMapping("/admin/api")
public class RestController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public RestController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/get-current-user")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserDetails currentUser) {
        User curUser = userService.findByUsername(currentUser.getUsername());
        return new ResponseEntity<>(curUser, HttpStatus.OK);
    }

    @GetMapping("/admin/get-all-roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return roles != null && !roles.isEmpty()
                ? new ResponseEntity<>(roles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/admin/getusers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/admin/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") long id) {
        final User user = userService.getUserbyId(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/admin/create-user")
    public ResponseEntity<List<User>> createUser(@RequestBody User user) {
        userService.save(user);
        List<User> users = userService.getUsers();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/admin/update-user")
    public ResponseEntity<List<User>> updateUser(@RequestBody User user) {
        userService.save(user);
        List<User> users = userService.getUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        List<User> users = userService.getUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
