package com.fabian.ejemplos.springboot.restclientexample.service;

import com.fabian.ejemplos.springboot.restclientexample.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TemplatePostService implements PostServiceJdbc {
    private static final Logger log = LoggerFactory.getLogger(TemplatePostService.class);
    private final JdbcTemplate jdbcTemplate;

    public TemplatePostService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Post> rowMapper = (rs, rowNum) -> new Post(
            rs.getInt("id"),
            rs.getInt("user_Id"),
            rs.getString("title"),
            rs.getString("body")
     );

    public List<Post> findAll() {
        var sql = "SELECT id,user_id,title,body FROM post";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Post> findById(int id) {
        var sql = "SELECT id,user_id,title,body FROM post WHERE id = ?";
        Post post = null;
        try {
            post = jdbcTemplate.queryForObject(sql,rowMapper,id);
        } catch (DataAccessException ex) {
            log.info("Post not found: " + id);
        }

        return Optional.ofNullable(post);
    }

    public void create(Post post) {
        String sql = "INSERT INTO post(id,user_id,title,body) values(?,?,?,?)";
        int insert = jdbcTemplate.update(sql,post.id(),post.userId(),post.title(),post.body());
        if(insert == 1) {
            log.info("New Post Created: " + post.title());
        }
    }

    public void batchCreate(Collection<Post> posts, int batchSize) {
        String sql = "INSERT INTO post(id,user_id,title,body) values(?,?,?,?)";
        int[][] rowsCreated = jdbcTemplate.batchUpdate(sql,
                posts,
                batchSize,
                (ps, argument) -> {
                    ps.setInt(1, argument.id());
                    ps.setInt(2, argument.userId());
                    ps.setString(3, argument.title());
                    ps.setString(4, argument.body());
                     });

        log.info("Batch Update Created: " + rowsCreated[0].length + " rows");
    }

    public void update(int id,Post post) {
        String sql = "update post set title = ?, user_id = ?, body = ? where id = ?";
        int update = jdbcTemplate.update(sql,post.title(),post.userId(),post.body(),post.id());
        if(update == 1) {
            log.info("Post Updated: " + post.title());
        }
    }

    public void delete(int id) {
        String sql = "delete from post where id = ?";
        int delete = jdbcTemplate.update(sql,id);
        if(delete == 1) {
            log.info("Post Deleted: " + id);
        }
    }

}