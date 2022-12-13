package com.yb.mongodb.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;

    @DocumentReference(lazy = true)
    private Company company;

    @DocumentReference(lazy = true, lookup = "{ 'users' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Project> projects = new HashSet<>();
}
