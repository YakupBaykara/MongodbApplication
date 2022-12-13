package com.yb.mongodb.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class UserDTO {

    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;

    @NotNull
    private String company;

    private Set<String> projects = new HashSet<>();
}
