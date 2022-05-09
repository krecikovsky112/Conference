package com.service.conference.Controllers;

import com.service.conference.Models.Lecture;
import com.service.conference.Models.Reservation;
import com.service.conference.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
public class ConferenceController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/showConferencePlan")
    public String showConferencePlan() {
        return "The conference lasts for 1 day: June 1, 2021. \n" +
                "It starts at 10:00 am and ends at 3:45 pm.\n" +
                "Each lecture lasts 1h 45m (15 minutes is a coffee break):\n" +
                "- the first lecture starts at 10:00 and lasts until 11:45.<\n" +
                "- the second one starts at 12:00 and ends at 13:45\n" +
                "- the third one starts at 14:00 and ends at 15:45\n" +
                "As part of the conference, 3 different thematic paths run in parallel are supported\n" +
                "Each lecture can accommodate a maximum of 5 listeners";
    }

    @GetMapping("reservationPlans/{login}")
    public List<Lecture> counterSpecialCharacters(@PathVariable String login) {
        String sql = "SELECT l.name,l.time FROM lectures as l, users as u WHERE u.name = '" + login +
                "' AND (u.reservation1 = l.id OR u.reservation2 = l.id OR u.reservation3 = l.id)";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Lecture.class));
    }

    @GetMapping("/users")
    public String printAllUsers() {
        List<Reservation> reservations =  (List<Reservation>) userRepository.findAll();

        StringBuilder result = new StringBuilder();
        for(Reservation reservation : reservations){
            result.append("Id: ").append(reservation.getId()).append("\n");
            result.append("Name: ").append(reservation.getName()).append("\n");
            result.append("Email: ").append(reservation.getEmail()).append("\n");
        }
        return result.toString();
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
}
