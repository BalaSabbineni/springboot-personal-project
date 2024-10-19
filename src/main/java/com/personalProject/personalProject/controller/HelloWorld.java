package com.personalProject.personalProject.controller;

import com.personalProject.personalProject.config.DetailsConfig;
import com.personalProject.personalProject.dto.Test;
import com.personalProject.personalProject.dto.TestEnum;
import com.personalProject.personalProject.dto.WebClientResponse;
import com.personalProject.personalProject.messaging.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@RestController
public class HelloWorld {

    //    @Value("${gopi.test}")
//    private String test;
    @Autowired
    private DetailsConfig detailsConfig;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private Map<TestEnum, Test> testEnumTestMap;

    @Autowired
    private Publisher publisher;

    @GetMapping("/hello")
    public String getHello() {
        return "Hello World...! " +
                detailsConfig.getName() + " " +
                detailsConfig.getDob() + " " +
                detailsConfig.getLocation();
    }

    @GetMapping("/test1")
    public WebClientResponse getResponse() {
        return webClientBuilder.baseUrl("http://localhost:8888")
                .build().get().uri("/test")
                .retrieve()
                .bodyToMono(WebClientResponse.class).block();
    }

    @GetMapping("/testClass")
    public String testClass() {
        System.out.println("hi Bala");
       publisher.sendMessage("Hi");

        return "Hello World...! " +
                detailsConfig.getName() + " " +
                detailsConfig.getDob() + " " +
                detailsConfig.getLocation();
    }
}
