package com.example.springrest.service;

import com.example.springrest.dao.UserRepository;
import com.example.springrest.entity.Role;
import com.example.springrest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public User save(User user) {

        if (user.getRoles() != null) {
            Set<Role> roleSet = new HashSet<>();
            for (Role roles : user.getRoles()) {
                roleSet.add(roleService.getRoleByName(roles.getName()));
            }
            user.setRoles(roleSet);
        }
        user.setEnabled(true);

        if (user.getId() == null) {
            user.setPass(bCryptPasswordEncoder.encode(user.getPass()));
        }

        if (user.getId() != null) {
            String oldPassword = getUserbyId(user.getId()).getPassword();

            if (user.getPass().isEmpty()) {
                user.setPass(oldPassword);
            } else {
                user.setPass(bCryptPasswordEncoder.encode(user.getPass()));
            }

        }

        userRepository.save(user);
        return user;
    }


    @Override
    public User getUserbyId(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByName(username);
    }

}