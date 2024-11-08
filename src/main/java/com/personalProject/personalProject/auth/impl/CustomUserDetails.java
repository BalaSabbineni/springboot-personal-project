package com.personalProject.personalProject.auth.impl;

import com.personalProject.personalProject.auth.entity.UserRegister;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final UserRegister userRegister;

    public CustomUserDetails(UserRegister userRegister) {
        this.userRegister = userRegister;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return userRegister.getPassWord();
    }

    @Override
    public String getUsername() {
        return userRegister.getUserName();
    }
}
