package com.personalProject.personalProject.auth.controller;

import com.personalProject.personalProject.auth.repository.UserAuthRepository;
import com.personalProject.personalProject.auth.dto.UserLogInDto;
import com.personalProject.personalProject.auth.dto.UserRegisterDto;
import com.personalProject.personalProject.auth.service.UserAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {

    private final UserAuthRepository userAuthRepository;
    private final UserAuthService userAuthService;

    public UserAuthController(UserAuthRepository userAuthRepository, UserAuthService userAuthService) {
        this.userAuthRepository = userAuthRepository;
        this.userAuthService = userAuthService;
    }


    @PostMapping("/signUp")
    public UserRegisterDto signUp(@RequestBody UserRegisterDto userRegisterDetails) {
        //return userAuthRepository.save(userRegisterDetails);
        return userAuthService.register(userRegisterDetails);
    }

    @PostMapping("/login") // returns token if login works
    public String login(@RequestBody UserLogInDto userLoginDetails) {
        // here need to verify userRegister(user details correct or not in db)
       // var user = userAuthRepository.findByUserName(userRegister.getUsername());
//        if (!Objects.isNull(user))
//            return "Success";
//        return "Failure";
        return userAuthService.verify(userLoginDetails);
    }
}
