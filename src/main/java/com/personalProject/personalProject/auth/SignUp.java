//package com.personalProject.personalProject.auth;
//
//import jakarta.persistence.*;
//import lombok.Data;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@Table(name = "sign_up")
//@Entity
//@Data
//public class SignUp implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String email;
//    private String password;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }
//
//    @Override
//    public String getUsername() {
//        return this.email;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}