package org.exzmple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BlogApp {
    public static void main(String[] args) {
        SpringApplication.run(BlogApp.class);
    }
}