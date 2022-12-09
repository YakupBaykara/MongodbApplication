package com.yb.mongodb.model;

import java.util.Set;

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

    @DocumentReference(lazy = true)
    private Company company;

    @DocumentReference(lazy = true)
    private Set<User> users;
}
