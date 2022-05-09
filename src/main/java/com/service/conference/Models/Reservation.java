package com.service.conference.Models;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(length = 3)
    private Integer reservation1;

    @Column(length = 3)
    private Integer reservation2;

    @Column(length = 3)
    private Integer reservation3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getReservation1() {
        return reservation1;
    }

    public void setReservation1(int reservation1) {
        this.reservation1 = reservation1;
    }

    public Integer getReservation2() {
        return reservation2;
    }

    public void setReservation2(int reservation2) {
        this.reservation2 = reservation2;
    }

    public Integer getReservation3() {
        return reservation3;
    }

    public void setReservation3(int reservation3) {
        this.reservation3 = reservation3;
    }
}
