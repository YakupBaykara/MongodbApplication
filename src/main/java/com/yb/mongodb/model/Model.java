package com.yb.mongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Setter
@Document
public class Model {

    @Id
    private String id;
    private String name;
    private String description;

    @DocumentReference(lazy = true)
    private Company company;

    @DocumentReference(lazy = true)
    private User user;

    @DocumentReference(lazy = true)
    private Project project;
}
