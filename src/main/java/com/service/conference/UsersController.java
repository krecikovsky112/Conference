package com.service.conference;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/addUser")
    private int saveUser(@RequestBody User user){
        userRepository.save(user);
        return user.getId();
    }

    @PutMapping("/updateEmail")
   private User updateEmail(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

}
