package pkrause.proj3.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pkrause.proj3.domain.MongoStudent;

import java.util.UUID;

@Repository
public interface ReactiveStudentRepository extends ReactiveMongoRepository<MongoStudent, UUID> {
}
