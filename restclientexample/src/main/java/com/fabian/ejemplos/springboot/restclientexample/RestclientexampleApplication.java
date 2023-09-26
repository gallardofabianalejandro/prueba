package com.fabian.ejemplos.springboot.restclientexample;

import com.fabian.ejemplos.springboot.restclientexample.model.Post;
import com.fabian.ejemplos.springboot.restclientexample.service.PostServiceJdbc;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestclientexampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestclientexampleApplication.class, args);
    }

    @Bean
    CommandLineRunner run(@Qualifier("PostJdbcClient") PostServiceJdbc postService
    ) {

        return args -> {
            postService.create(new Post(1, 1, "title1", "body1"));
            postService.create(new Post(2, 1, "title2", "body2"));
            postService.create(new Post(3, 1, "title3", "body3"));
            postService.create(new Post(4, 1, "title4", "body4"));
            postService.create(new Post(5, 1, "title5", "body5"));
            postService.create(new Post(6, 1, "title6", "body6"));
            postService.create(new Post(7, 1, "title7", "body7"));
            postService.create(new Post(8, 1, "title8", "body8"));
            postService.create(new Post(9, 1, "title9", "body9"));
        };
    }
}
