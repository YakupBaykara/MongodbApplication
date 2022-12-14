package com.yb.mongodb.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document
public class Project {

    @Id
    private String id;
    private String name;
    private String description;
    private String ownerId;
    private String imagePath;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdatedAt;
    private boolean isActive = true;

    @DocumentReference(lazy = true)
    private Company company;

    @DocumentReference(lazy = true)
    private Set<User> users =  new HashSet<>();
}
