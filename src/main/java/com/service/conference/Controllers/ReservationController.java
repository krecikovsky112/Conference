package com.service.conference.Controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.service.conference.Models.Reservation;
import com.service.conference.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addReservation")
    private String addReservation(@RequestBody Reservation reservation) throws IOException {

        if (userRepository.countByReservation1(reservation.getReservation1()) < 5 &&
                userRepository.countByReservation2(reservation.getReservation2()) < 5 &&
                userRepository.countByReservation3(reservation.getReservation3()) < 5 &&
                (reservation.getReservation1() + 3) != reservation.getReservation2() &&
                (reservation.getReservation1() + 6) != reservation.getReservation3() &&
                (reservation.getReservation2() + 3) != reservation.getReservation3()) {
            if (userRepository.findByName(reservation.getName()).size() == 0) {
                userRepository.save(reservation);

                saveToFile(reservation);

                return "Succesfull reservation!";
            } else {
                return "Given login is busy!";
            }
        } else {
            return "Cannot reservation on this lecture with this themas!";
        }
    }

    @PutMapping("/updateEmail")
    private Reservation updateEmail(@RequestBody Reservation reservation) {
        userRepository.save(reservation);
        return reservation;
    }

    @PutMapping("/cancelReservation")
    private Reservation cancelReservation(@RequestBody Reservation reservation) {
        userRepository.save(reservation);
        return reservation;
    }


    private void saveToFile(Reservation reservation) throws IOException {
        String filePath = "./notifications.txt";
        FileWriter fileWriter = new FileWriter(filePath, true);

        fileWriter.append("Data: " + java.time.LocalDate.now().toString() + "\n");
        fileWriter.append("To whom: " + reservation.getName() + "\n");
        fileWriter.append("Reservation 10:00 - 11:45: " + reservation.getReservation1() + "\n" +
                "Reservation 12:00 - 13-45: " + reservation.getReservation2() + "\n" +
                "Reservation 14:00 - 15:45: " + reservation.getReservation3() + "\n");

        fileWriter.close();
    }
}
