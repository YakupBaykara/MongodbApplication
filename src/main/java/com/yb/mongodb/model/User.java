package com.yb.mongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
@Getter
@Setter
public class User {

    @Id
    private String id;

    private String name;

    @DocumentReference(lazy = true)
    private Company company;

}
