package com.personalProject.personalProject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

@SpringBootApplication()
@OpenAPIDefinition // For SwaggerUI
@EnableScheduling
@EnableCaching
public class PersonalProjectApplication {

    private static final Logger log = LoggerFactory.getLogger(PersonalProjectApplication.class);

   // @Scheduled(fixedRate = 5000L)
    //  @Scheduled(fixedRateString = "PT5S")
    // 	@Scheduled(fixedDelay = 5000L, initialDelay= 2000L)
    //  @Scheduled(cron = "*/2 * * * * *")
    public void job1() {
        log.info("Scheduler... job 1 {}", Instant.now());
    }

    /*
     * normally If we have multiple jobs, all will execute in single thread and in sequence order.
     * so, it's not good.
     * So, sprig provides a pool size option, that we can enable in application properties.
     * eg. spring.task.scheduling.pool.size = 10
     */
    // @Scheduled(cron = "*/5 * * * * *")
    public void job2() {
        log.info("Scheduler... job 2 {}", Instant.now());
    }


    public static void main(String[] args) {
        SpringApplication.run(PersonalProjectApplication.class, args);
    }

}
