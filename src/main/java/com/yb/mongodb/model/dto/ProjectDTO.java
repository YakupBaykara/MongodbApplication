package com.yb.mongodb.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class ProjectDTO {

    private String id;
    private String name;
    private String description;
    private String ownerId;
    private String imagePath;
    private LocalDateTime lastUpdatedAt;

    @NotNull
    private String company;

    private Set<String> users = new HashSet<>();
}
