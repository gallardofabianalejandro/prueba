package com.fabian.ejemplos.springboot.restclientexample.controller;

import com.fabian.ejemplos.springboot.restclientexample.model.Post;
import com.fabian.ejemplos.springboot.restclientexample.service.PostServiceJdbc;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jdbc/posts")
public class PostControllerJdbc {


    private final PostServiceJdbc service;

    public PostControllerJdbc(@Qualifier("PostJdbcClient") PostServiceJdbc service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> getPosts() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Integer id) {
        return service.findById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody Post post) {
        service.create(post);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable Integer id, @RequestBody Post post) {
        service.update(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Integer id) {
        service.delete(id);
    }
}
