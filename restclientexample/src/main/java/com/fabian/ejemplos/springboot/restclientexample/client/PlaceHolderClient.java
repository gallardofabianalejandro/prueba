package com.fabian.ejemplos.springboot.restclientexample.client;

import com.fabian.ejemplos.springboot.restclientexample.model.Post;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

public interface PlaceHolderClient {

    @GetExchange("/posts")
    List<Post> getPosts();

    @GetExchange("/posts/{id}")
    Post getPostById(@PathVariable Integer id);

    @PostExchange("/posts")
    Post createPost(Post post);

    @PutExchange("/posts/{id}")
    Post update(@PathVariable Integer id, Post post);

    @DeleteExchange("/posts/{id}")
    void delete(@PathVariable Integer id);
}
