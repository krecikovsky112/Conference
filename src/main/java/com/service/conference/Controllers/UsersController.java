package com.service.conference.Controllers;

import java.util.List;

import com.service.conference.Models.User;
import com.service.conference.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

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
    private User updateEmail(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @PutMapping("/cancelReservation")
    private User cancelReservation(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }
}
