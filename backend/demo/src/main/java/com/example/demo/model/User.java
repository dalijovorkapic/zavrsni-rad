package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class User {

    private final int user_id;

    @NotBlank
    private final String email;

    @NotBlank
    private final String name;

    @NotBlank
    private final String password;


    public User(
            @JsonProperty("user_id") int user_id,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password)
    {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

}
