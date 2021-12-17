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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    String password;

    @Autowired
    public RestController(UserServiceImpl userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @GetMapping("/get-current-user")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserDetails currentUser) {
        User curUser = userService.findByUsername(currentUser.getUsername());
        return new ResponseEntity<>(curUser, HttpStatus.OK);
    }

    @GetMapping("/admin/get-all-roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return roles != null &&  !roles.isEmpty()
                ? new ResponseEntity<>(roles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/admin/getusers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/admin/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") long id) {
        final User user = userService.getUserbyId(id);
        password = user.getPassword();
        System.out.println(""+userService.findByUsername(user.getName()).getPass());

        System.out.println(user.getPass());
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/admin/create-user")
    public ResponseEntity<List<User>> createUser(@RequestBody User user) {
        System.out.println(user.getPass());
        saveOrUpdateUser(user);
        List<User> users = userService.getUsers();
        System.out.println(user.getPass());
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/admin/update-user")
     public ResponseEntity<List<User>> updateUser(@RequestBody User user) {
        System.out.println("update "+user.getPass());
        saveOrUpdateUser(user);
        System.out.println("update "+user.getPass());
        List<User> users = userService.getUsers();
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void saveOrUpdateUser(User user){
        if (user.getRoles() != null) {
            Set<Role> roleSet = new HashSet<>();
            for (Role roles : user.getRoles()) {
                roleSet.add(roleService.getRoleByName(roles.getName()));
            }
            user.setRoles(roleSet);
        }
        System.out.println("pass0"+user.getPass());
        if (!password.equals(user.getPassword())){
            user.setPass(bCryptPasswordEncoder.encode(user.getPassword()));
            System.out.println("change newcrypt" +user.getPass());
        }

        user.setEnabled(true);
        userService.save(user);
        System.out.println("pass2" +user.getPass());
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        List<User> users = userService.getUsers();
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
