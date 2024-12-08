package org.example.puppyhome.dbstorage.repository;

import org.example.puppyhome.dbstorage.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {
}