package com.personalProject.personalProject.auth.service;

import com.personalProject.personalProject.auth.dto.UserLogInDto;
import com.personalProject.personalProject.auth.dto.UserRegisterDto;
import com.personalProject.personalProject.auth.entity.UserRegister;
import com.personalProject.personalProject.auth.jwt.JwtService;
import com.personalProject.personalProject.auth.repository.UserAuthRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    private final UserAuthRepository userAuthRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserAuthService(UserAuthRepository userAuthRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {
        this.userAuthRepository = userAuthRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UserRegisterDto register(UserRegisterDto userRegisterDetails) {
        // Encrypting password and saving to db as encrypted pwd.
        userRegisterDetails.setPassWord(bCryptPasswordEncoder.encode(userRegisterDetails.getPassWord()));
        UserRegister userRegister = UserRegister.builder()
                .userName(userRegisterDetails.getUserName())
                .passWord(userRegisterDetails.getPassWord()).build();
        var user = userAuthRepository.save(userRegister);
        return UserRegisterDto.builder().userName(user.getUserName()).passWord(user.getPassWord()).build();
    }

    public String verify(UserLogInDto userLoginDetails) {
        //  AuthenticationManager is used to authenticate the user
        var authenticate = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(userLoginDetails.getUserName(), userLoginDetails.getPassWord()));

//        var user = userAuthRepository.findByUserName(userLoginDetails.getUserName());
//        if (!Objects.isNull(user))
//            return "Success";
//        return "Failed";

//        if (authenticate.isAuthenticated()) { // Instead of If-else, always use below ternary operator
//            return jwtService.generateToken(userLoginDetails);
//        }

        return authenticate.isAuthenticated() ? jwtService.generateToken(userLoginDetails) : "Failure";
    }
}
