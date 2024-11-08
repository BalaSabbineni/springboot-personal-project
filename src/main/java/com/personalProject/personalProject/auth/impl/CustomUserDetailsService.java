package com.personalProject.personalProject.auth.impl;

import com.personalProject.personalProject.auth.repository.UserAuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/*
 * This is the implementation for UserDetailsService to talk with Db and
 * checks user/pwd valid or not.
 */
@Component
//@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    public CustomUserDetailsService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    /*
     * This method returns UserDetails, so we need to create CustomUserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userAuthRepository.findByUserName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found.....");
        }
        return new CustomUserDetails(user.get());

    }
}
