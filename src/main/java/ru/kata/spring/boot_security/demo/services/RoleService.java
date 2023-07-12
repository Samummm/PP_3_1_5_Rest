package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles();

    Role getRoleByName(String name);

    Role findByName(String name);
}
