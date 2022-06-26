package ru.jhecks.service;

import ru.jhecks.model.Role;

import java.util.List;

public interface RoleService {
    void createRole(Role role);
    void deleteRole(Role role);
    void updateRole(Role role);
    Role getRole(String name);
    Role getRole(long id);
    List<Role> getAllRoles();
}
