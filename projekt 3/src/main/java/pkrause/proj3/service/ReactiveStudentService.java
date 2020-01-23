package pkrause.proj3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkrause.proj3.domain.MongoStudent;
import pkrause.proj3.repository.ReactiveStudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@Service
public class ReactiveStudentService implements IReactiveService<MongoStudent, UUID> {
    private ReactiveStudentRepository repository;

    @Autowired
    public ReactiveStudentService(ReactiveStudentRepository repository) {
        this.repository = repository;
    }

    public Mono<MongoStudent> save(@Valid MongoStudent mongoStudent) {
        return this.repository.save(mongoStudent);
    }

    public Mono<MongoStudent> read(UUID id) {
        return this.repository.findById(id);
    }

    public Flux<MongoStudent> read() {
        return this.repository.findAll();
    }

    public Mono<MongoStudent> update(UUID id, MongoStudent mongoStudent) {
        mongoStudent.setId(id);
        return this.repository.save(mongoStudent);
    }

    public Mono<Void> delete(UUID id) {
        return this.repository.deleteById(id);
    }
}