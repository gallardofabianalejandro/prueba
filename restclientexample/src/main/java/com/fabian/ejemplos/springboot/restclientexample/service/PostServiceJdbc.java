package com.fabian.ejemplos.springboot.restclientexample.service;

import com.fabian.ejemplos.springboot.restclientexample.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostServiceJdbc {
    public List<Post> findAll();
    public Optional<Post> findById(int id);
    public void create(Post post);
    public void update(int id, Post post);
    public void delete(int id);
}
