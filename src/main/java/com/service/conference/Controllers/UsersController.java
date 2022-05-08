package com.service.conference.Controllers;

import java.util.List;

import com.service.conference.Models.Lecture;
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

    @PostMapping("/addReservation")
    private int addReservation(@RequestBody User user) {
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

    @GetMapping("/listOfLectures")
    public String showListLectures() {
        double reservation1 = 0, reservation2 = 0, reservation3 = 0, all = 0;
        for (int i = 1; i < 10; i++) {
            if (i <= 3) {
                reservation1 += userRepository.countByReservation1(i);
            } else if (i <= 6) {
                reservation2 += userRepository.countByReservation2(i);
            } else {
                reservation3 += userRepository.countByReservation3(i);
            }
        }
        all += reservation1 + reservation2+reservation3;

        return "Lecture 1: " +  (reservation1/all* 100) + " % \n" +
                "Lecture 2: " +  (reservation2/all* 100)  + " % \n"+
                "Lecture 3: " +  (reservation3/all* 100)+ " % \n" ;
    }
}
