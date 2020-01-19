package pkrause.proj3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkrause.proj3.domain.Group;
import pkrause.proj3.repository.ReactiveGroupRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
public class ReactiveGroupService implements IReactiveService<Group, Long> {
    private ReactiveGroupRepository repository;

    @Autowired
    public ReactiveGroupService(ReactiveGroupRepository repository) {
        this.repository = repository;
    }

    public Mono<Group> save(@Valid Group group) {
        return this.repository.save(group);
    }

    public Mono<Group> read(Long id) {
        return this.repository.findById(id);
    }

    public Flux<Group> read() {
        return this.repository.findAll();
    }

    public Mono<Group> update(Long id, Group group) {
        group.setId(id);
        return this.repository.save(group);
    }

    public Mono<Void> delete(Long id) {
        return this.repository.deleteById(id);
    }
}
