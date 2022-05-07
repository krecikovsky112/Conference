package com.service.conference;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

//    private final UserRepository repo;
//
//    public UsersController(UserRepository repo) {
//        this.repo = repo;
//    }

    @Autowired
    JdbcTemplate jdbcTemplate;

//    @GetMapping("/users")
//    public List<User> listAll() {
//        return repo.findAll();
//    }

    @GetMapping("/users")
    public List<User> listAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
    }

    @GetMapping("/sample")
    public String show() {
        return "sample";
    }

}
