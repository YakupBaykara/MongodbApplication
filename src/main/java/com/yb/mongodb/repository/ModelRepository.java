package com.yb.mongodb.repository;

import com.yb.mongodb.model.Model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends MongoRepository<Model, String> {

    List<Model> findByProjectId(String projectId);
    List<Model> findByUserId(String userId);
    List<Model> findByCompanyId(String companyId);
}
