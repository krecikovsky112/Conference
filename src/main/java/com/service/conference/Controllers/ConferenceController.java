package com.service.conference.Controllers;

import com.service.conference.Models.Lecture;
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

    @GetMapping("/showConferencePlan")
    public String showConferencePlan() {
        return "The conference lasts for 1 day: June 1, 2021. <br>" +
                "It starts at 10:00 am and ends at 3:45 pm.<br>" +
                "Each lecture lasts 1h 45m (15 minutes is a coffee break):<br>" +
                "- the first lecture starts at 10:00 and lasts until 11:45.<br>" +
                "- the second one starts at 12:00 and ends at 13:45<br>" +
                "- the third one starts at 2 p.m. and ends at 3:45 p.m.<br>" +
                "As part of the conference, 3 different thematic paths run in parallel are supported<br>" +
                "Each lecture can accommodate a maximum of 5 listeners";
    }

    @GetMapping("reservationPlans/{login}")
    public List<Lecture> counterSpecialCharacters(@PathVariable String login) {
        String sql = "SELECT l.name,l.time FROM lectures as l, users as u WHERE u.name = '" + login +
                "' AND (u.reservation1 = l.id OR u.reservation2 = l.id OR u.reservation3 = l.id)";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Lecture.class));
    }



}
