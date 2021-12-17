package com.example.springrest.service;

import com.example.springrest.dao.UserRepository;
import com.example.springrest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public User save(User user) {
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