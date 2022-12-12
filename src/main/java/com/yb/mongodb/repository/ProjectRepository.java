package com.yb.mongodb.repository;

import com.yb.mongodb.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {

    List<Project> findAllByCompanyId(String companyId);
    List<Project> findAllByUsersId(String userId);
    List<Project> findAllByCompanyIdAndUsersId(final String companyId, final String userId);
}
