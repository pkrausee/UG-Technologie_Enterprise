package pkrause.proj3.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pkrause.proj3.domain.Group;

public interface ReactiveGroupRepository extends ReactiveMongoRepository<Group, Long> {
}
