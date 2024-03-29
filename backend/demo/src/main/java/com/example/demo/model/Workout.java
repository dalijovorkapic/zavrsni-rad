package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Workout {

    private final int workout_id;

    @NotBlank
    private final String name;

    private final String complexity;

    private final int duration;

    private final int user_id;

    public Workout(
            @JsonProperty("workout_id") int workout_id,
            @JsonProperty("name") String name,
            @JsonProperty("complexity") String complexity,
            @JsonProperty("duration") int duration,
            @JsonProperty("user_id") int user_id)
    {
        this.workout_id = workout_id;
        this.name = name;
        this.complexity = complexity;
        this.duration = duration;
        this.user_id = user_id;
    }
    public int getWorkout_id() {
        return workout_id;
    }
    public String getName() {
        return name;
    }
    public String getComplexity() {
        return complexity;
    }
    public int getDuration() {
        return duration;
    }
    public int getUser_id() {
        return user_id;
    }

}
