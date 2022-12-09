package com.yb.mongodb.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class ProjectDTO {

    private String id;

    private String name;

    @NotNull
    private String company;

    private Set<String> users;
}
