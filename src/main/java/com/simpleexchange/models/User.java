package com.simpleexchange.models;

import com.simpleexchange.common.enums.UserRole;

public class User {
    private String username;
    private String password;
    private UserRole roleId;

    public User() {}

    public User(String username, String password, UserRole roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRoleId() {
        return roleId;
    }

    public void setRoleId(UserRole roleId) {
        this.roleId = roleId;
    }
}
