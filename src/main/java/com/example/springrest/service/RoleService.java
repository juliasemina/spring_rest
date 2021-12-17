package com.example.springrest.service;


import com.example.springrest.entity.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles();
    public Role getRoleByName(String rolename);

}
