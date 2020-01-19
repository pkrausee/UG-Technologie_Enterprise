package pkrause.proj3.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pkrause.proj3.domain.Student;

import java.util.UUID;

public interface ReactiveStudentRepository extends ReactiveMongoRepository<Student, UUID> {
}
