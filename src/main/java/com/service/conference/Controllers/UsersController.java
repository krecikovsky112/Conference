package com.service.conference.Controllers;

import java.io.FileWriter;
import java.io.IOException;
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
    private String addReservation(@RequestBody User user) throws IOException {

        if (userRepository.findByName(user.getName()).size() == 0) {
            userRepository.save(user);

            saveToFile(user);

            return "Succesfull reservation!";
        } else {
            return "Given login is busy!";
        }
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
        all += reservation1 + reservation2 + reservation3;

        return "Lecture 1: " + (reservation1 / all * 100) + " % \n" +
                "Lecture 2: " + (reservation2 / all * 100) + " % \n" +
                "Lecture 3: " + (reservation3 / all * 100) + " % \n";
    }

    @GetMapping("/listOfThemas")
    public String showListThemas() {
        double thema1 = 0, thema2 = 0, thema3 = 0, all = 0;
        for (int i = 1; i < 10; i++) {
            if (i == 1 || i == 4 || i == 7) {
                thema1 += getCounterThema(i);
            } else if (i == 2 || i == 5 || i == 8) {
                thema2 += getCounterThema(i);
            } else {
                thema3 += getCounterThema(i);
            }
        }
        all = thema1 + thema2 + thema3;

        return "Thema 1: " + (thema1 / all * 100) + " % \n" +
                "Thema 2: " + (thema2 / all * 100) + " % \n" +
                "Thema 3: " + (thema3 / all * 100) + " % \n";
    }

    private double getCounterThema(int i) {
        double thema = 0;
        thema += userRepository.countByReservation1(i);
        thema += userRepository.countByReservation2(i);
        thema += userRepository.countByReservation3(i);
        return thema;
    }

    private void saveToFile(User user) throws IOException {
        String filePath = "./notifications.txt";
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(filePath);
        fileWriter.append("Data: " + java.time.LocalDate.now().toString() +"\n");
        fileWriter.append("To whom: " + user.getName() + "\n");
        fileWriter.append("Reservation 10:00 - 11:45: " + user.getReservation1() + "\n" +
                "Reservation 12:00 - 13-45: " + user.getReservation2() + "\n" +
                "Reservation 14:00 - 15:45: " + user.getReservation3() + "\n");

        fileWriter.close();
    }
}
