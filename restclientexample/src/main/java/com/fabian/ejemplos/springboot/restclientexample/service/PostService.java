package com.fabian.ejemplos.springboot.restclientexample.service;

import com.fabian.ejemplos.springboot.restclientexample.model.Post;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class PostService {

    private final RestClient client;

    private PostService() {
        this.client = RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

    }

    public List<Post> getPosts() {
        return client.get().uri("/posts").retrieve().body(new ParameterizedTypeReference<List<Post>>() {

        });
    }


    public Post getPostById(Integer id) {

        return client.get().uri("/posts/{id}", id).retrieve().body(Post.class);
    }

    public Post createPost(Post post) {
        return client.post().uri("/posts").contentType(MediaType.APPLICATION_JSON).body(post).retrieve().body(Post.class);
    }

    public Post update(Integer id, Post post) {
        return client.put().uri("/posts/{id}", id).contentType(MediaType.APPLICATION_JSON).body(post).retrieve().body(Post.class);
    }

    public void delete(Integer id) {

        client.delete().uri("/posts/{id}", id).retrieve().toBodilessEntity();
    }
}
