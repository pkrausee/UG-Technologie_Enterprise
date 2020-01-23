package pkrause.proj3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkrause.proj3.domain.MongoGroup;
import pkrause.proj3.repository.ReactiveGroupRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
public class ReactiveGroupService implements IReactiveService<MongoGroup, Long> {
    private ReactiveGroupRepository repository;

    @Autowired
    public ReactiveGroupService(ReactiveGroupRepository repository) {
        this.repository = repository;
    }

    public Mono<MongoGroup> save(@Valid MongoGroup mongoGroup) {
        return this.repository.save(mongoGroup);
    }

    public Mono<MongoGroup> read(Long id) {
        return this.repository.findById(id);
    }

    public Flux<MongoGroup> read() {
        return this.repository.findAll();
    }

    public Mono<MongoGroup> update(Long id, MongoGroup mongoGroup) {
        mongoGroup.setId(id);
        return this.repository.save(mongoGroup);
    }

    public Mono<Void> delete(Long id) {
        return this.repository.deleteById(id);
    }
}
