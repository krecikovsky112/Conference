package com.service.conference.Repository;

import com.service.conference.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    long countByReservation1(int a);
    long countByReservation2(int a);
    long countByReservation3(int a);
}
