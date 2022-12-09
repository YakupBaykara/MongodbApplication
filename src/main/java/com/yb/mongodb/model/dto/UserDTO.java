package com.yb.mongodb.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {

    private String id;

    private String name;

    @NotNull
    private String company;
}
