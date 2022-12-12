package com.yb.mongodb.repository;

import com.yb.mongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<List<User>> findByCompanyId(String companyId);
    Optional<Integer> countByCompanyId(String companyId);
}
