package pkrause.proj3.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pkrause.proj3.domain.MongoGroup;

@Repository
public interface ReactiveGroupRepository extends ReactiveMongoRepository<MongoGroup, Long> {
}
