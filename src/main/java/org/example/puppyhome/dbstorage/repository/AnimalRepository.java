package org.example.puppyhome.dbstorage.repository;

import org.example.puppyhome.dbstorage.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimalRepository extends MongoRepository<Animal, String> {
}