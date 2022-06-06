package com.fer.hr.zavrsni.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class Schedule {

    private final int Id;
    private final String Subject;
    private final Timestamp StartTime;
    private final Timestamp EndTime;

    public Schedule(@JsonProperty  int id, @JsonProperty  String subject, @JsonProperty Timestamp startTime, @JsonProperty Timestamp endTime) {
        Id = id;
        Subject = subject;
        StartTime = startTime;
        EndTime = endTime;
    }

    public int getId() {
        return Id;
    }

    public String getSubject() {
        return Subject;
    }

    public Timestamp getStartTime() {
        return StartTime;
    }

    public Timestamp getEndTime() {
        return EndTime;
    }
}
