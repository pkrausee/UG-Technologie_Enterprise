package pkrause.proj3.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pkrause.proj3.domain.Student;

import java.util.UUID;

@Repository
public interface ReactiveStudentRepository extends ReactiveCrudRepository<Student, UUID> {
}
