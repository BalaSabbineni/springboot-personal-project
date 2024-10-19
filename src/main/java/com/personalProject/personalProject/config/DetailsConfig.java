package com.personalProject.personalProject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "details.personal")
public class DetailsConfig {

    private String name;
    private String dob;
    private String location;

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
