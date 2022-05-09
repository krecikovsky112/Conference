package com.service.conference.Repository;

import com.service.conference.Models.Reservation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,Integer> {
    long countByReservation1(int a);
    long countByReservation2(int a);
    long countByReservation3(int a);

    List<Reservation> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Reservation AS r SET r.email = ?1 where r.id = ?2")
    int updateEmail(String email, int id);
}
