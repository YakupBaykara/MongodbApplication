package com.yb.mongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Model {

    @Id
    private String id;
    private String name;

    private String userId;
    private String projectId;
}
