package com.example.springrest.dao;

import com.example.springrest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.name=?1")
//    User findByUserName(String username);

    User findUserByName(String username);
    User findByEmail(String email);
}