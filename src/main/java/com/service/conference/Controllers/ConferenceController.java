package com.service.conference.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConferenceController {

    @GetMapping("/showConferencePlan")
    public String showConferencePlan() {
        return "The conference lasts for 1 day: June 1, 2021. <br>" +
                "It starts at 10:00 am and ends at 3:45 pm.<br>" +
                "Each lecture lasts 1h 45m (15 minutes is a coffee break):<br>" +
                "- the first lecture starts at 10:00 and lasts until 11:45.<br>" +
                "- the second one starts at 12:00 and ends at 13:45<br>" +
                "- the third one starts at 2 p.m. and ends at 3:45 p.m.<br>"+
                "As part of the conference, 3 different thematic paths run in parallel are supported<br>" +
                "Each lecture can accommodate a maximum of 5 listeners";
    }
}
