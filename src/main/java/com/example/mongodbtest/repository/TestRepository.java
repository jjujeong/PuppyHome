package com.example.mongodbtest.repository;

import com.example.mongodbtest.model.TestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<TestEntity, String> {
}
