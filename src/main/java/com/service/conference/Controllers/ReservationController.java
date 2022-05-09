package com.service.conference.Controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.service.conference.Models.Reservation;
import com.service.conference.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping("/addReservation")
    private String addReservation(@RequestBody Reservation reservation) throws IOException {

        if (reservationRepository.countByReservation1(reservation.getReservation1()) < 5 &&
                reservationRepository.countByReservation2(reservation.getReservation2()) < 5 &&
                reservationRepository.countByReservation3(reservation.getReservation3()) < 5 &&
                (reservation.getReservation1() + 3) != reservation.getReservation2() &&
                (reservation.getReservation1() + 6) != reservation.getReservation3() &&
                (reservation.getReservation2() + 3) != reservation.getReservation3()) {
            if (reservationRepository.findByName(reservation.getName()).size() == 0) {
                reservationRepository.save(reservation);

                saveToFile(reservation);

                return "Succesfull reservation!";
            } else {
                return "Given login is busy!";
            }
        } else {
            return "Cannot reservation on this lecture with this themas!";
        }
    }

    @PutMapping("/updateEmail/{name}/{newEmail}")
    private String updateEmail(@PathVariable String name, @PathVariable String newEmail) {
//        reservationRepository.save(reservation);

        List<Reservation> reservations = reservationRepository.findByName(name);
        reservationRepository.updateEmail(newEmail, reservations.get(0).getId());

        return "Change email to: " + newEmail;
    }

    @PutMapping("/cancelReservation")
    private Reservation cancelReservation(@RequestBody Reservation reservation) {
        reservationRepository.save(reservation);
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
