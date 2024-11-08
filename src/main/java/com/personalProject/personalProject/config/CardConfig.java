package com.personalProject.personalProject.config;

import com.personalProject.personalProject.dto.AboutCard;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class CardConfig {

    @Bean
    @ConfigurationProperties("about-card-info")
//    @Profile("dev")
    public AboutCard aboutCard() {
        return new AboutCard();
    }
}
