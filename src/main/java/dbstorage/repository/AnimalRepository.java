package dbstorage.repository;

import dbstorage.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimalRepository extends MongoRepository<Animal, String> {
}
