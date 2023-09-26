package com.fabian.ejemplos.springboot.restclientexample.service;

import com.fabian.ejemplos.springboot.restclientexample.model.Post;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service("PostJdbcClient")
@Transactional
public class PostJdbcClient implements PostServiceJdbc {
    private final JdbcClient jdbcClient;

    public PostJdbcClient(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Post> findAll() {
        return jdbcClient.sql("SELECT id,user_id,title,body FROM post")
                .query(Post.class)
                .list()
                ;
    }

    @Override
    public Optional<Post> findById(int id) {
        return jdbcClient.sql("SELECT id,user_id,title,body FROM post WHERE id = :id")
                .param("id", id)
                .query(Post.class)
                .optional();
    }

    @Override
    public void create(Post post) {
        int create = jdbcClient.sql("INSERT INTO post(id,user_id,title,body) values(?,?,?,?)")
                .params(List.of(post.id(), post.userId(), post.title(), post.body()))
                .update();



        Assert.state(create == 1, "New Post Created: " + post.title());
    }

    @Override
    public void update(int id, Post post) {
        int update = jdbcClient.sql("UPDATE post SET user_id = ?, title = ?, body = ? WHERE id = ?")
                .params(List.of(post.userId(), post.title(), post.body(), id))
                .update();

        Assert.state(update == 1, "update Post Created: " + post.title());

    }

    @Override
    public void delete(int id) {
        int deleted = jdbcClient.sql("delete from post where id = :id")
                .param("id", id)
                .update();

        Assert.state(deleted == 1, "Post Deleted: " + id);
    }
}
