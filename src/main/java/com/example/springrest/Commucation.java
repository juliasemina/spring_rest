package com.example.springrest;

import com.example.springrest.entity.Role;
import com.example.springrest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Commucation {

    private final String URL="http://localhost:8080";

    private final RestTemplate restTemplate;

    @Autowired
    public Commucation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> allUsers(){
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL+"/admin/getusers", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> listUsers = responseEntity.getBody();
        return listUsers;
    }

    public List<Role> allRoles(){
        ResponseEntity<List<Role>> responseEntity =
                restTemplate.exchange(URL+"/admin/get-all-roles", HttpMethod.GET, null, new ParameterizedTypeReference<List<Role>>() {});
        List<Role> listRoles = responseEntity.getBody();
        return listRoles;
    }

    public User getCurrentUser(){
        User user = restTemplate.getForObject(URL+"/admin/get-current-user", User.class);
        return user;
    }

    public void saveOrUpdateUser(User user){
        Long id = user.getId();
        if (id==0){
            ResponseEntity<String> responseEntity = restTemplate
                    .postForEntity(URL+"/admin/create-user", user, String.class);
            System.out.println(responseEntity.getBody());
        }
        else {
            restTemplate
                    .put(URL+"/admin/update-user", user);

        }
    }

    public void deleteUser(Long id){
        restTemplate.delete(URL+"/admin/delete/"+id);
    }
}
