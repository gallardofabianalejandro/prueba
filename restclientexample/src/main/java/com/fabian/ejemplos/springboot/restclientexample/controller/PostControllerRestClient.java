package com.fabian.ejemplos.springboot.restclientexample.controller;

import com.fabian.ejemplos.springboot.restclientexample.client.PlaceHolderClient;
import com.fabian.ejemplos.springboot.restclientexample.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostControllerRestClient {

    //private final PostService service;
    private final PlaceHolderClient service;

    public PostControllerRestClient(PlaceHolderClient service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> getPosts() {
        return service.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Integer id) {
        return service.getPostById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return service.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Integer id, @RequestBody Post post) {
        return service.update(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Integer id) {
        service.delete(id);
    }
}
