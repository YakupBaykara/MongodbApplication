package com.yb.mongodb.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
public class Project {

    @Id
    private String id;

    private String name;

    @DocumentReference(lazy = true)
    private Company company;

    @DocumentReference(lazy = true)
    private Set<User> users;
}
