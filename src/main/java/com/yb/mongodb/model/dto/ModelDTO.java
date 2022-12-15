package com.yb.mongodb.model.dto;

import com.yb.mongodb.model.Company;
import com.yb.mongodb.model.Project;
import com.yb.mongodb.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ModelDTO {

    private String id;
    private String name;
    private String description;

    private String company;
    private String user;
    private String project;
}
