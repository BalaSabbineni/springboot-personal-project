package com.personalProject.personalProject.config;

import com.personalProject.personalProject.dto.Test;
import com.personalProject.personalProject.dto.TestEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public Map<TestEnum, Test> getTestClass() {
       return new HashMap<>();
    }
}
