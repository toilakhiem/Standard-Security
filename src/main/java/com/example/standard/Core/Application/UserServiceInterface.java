package com.example.standard.Core.Application;

import com.example.standard.Core.Entity.Permission;
import com.example.standard.Core.Entity.Role;
import com.example.standard.Core.Entity.User;

public interface UserServiceInterface {
    void saveUser(User user);
    void saveRole(Role role);
    void savePermission(Permission permission);
}
