package com.yb.mongodb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document
public class Company {

    @Id
    private String id;

    private String name;
    private String phoneNumber;
    private String email;

    @Builder.Default
    private Boolean isActive = Boolean.TRUE;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
