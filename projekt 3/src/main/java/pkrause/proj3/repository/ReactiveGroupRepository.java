package pkrause.proj3.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pkrause.proj3.domain.Group;

@Repository
public interface ReactiveGroupRepository extends ReactiveCrudRepository<Group, Long> {
}
