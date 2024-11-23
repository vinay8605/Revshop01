package com.revshop1;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@SpringBootApplication
@ComponentScan("com.revshop1")
@EnableJpaRepositories(basePackages = "com.revshop1.repository")
public class RevShop1Application implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(RevShop1Application.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
}
