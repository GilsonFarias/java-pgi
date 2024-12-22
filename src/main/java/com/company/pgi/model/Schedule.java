package com.company.pgi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime date;

    @Column(length = 250)
    private String observation;

    private Integer first_time;

    private Integer return_consultation;

    public Schedule(){}

    public Schedule(Integer id, LocalDateTime date, String observation, Integer first_time,
            Integer return_consultation) {
        this.id = id;
        this.date = date;
        this.observation = observation;
        this.first_time = first_time;
        this.return_consultation = return_consultation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Integer getFirst_time() {
        return first_time;
    }

    public void setFirst_time(Integer first_time) {
        this.first_time = first_time;
    }

    public Integer getReturn_consultation() {
        return return_consultation;
    }

    public void setReturn_consultation(Integer return_consultation) {
        this.return_consultation = return_consultation;
    }


}
