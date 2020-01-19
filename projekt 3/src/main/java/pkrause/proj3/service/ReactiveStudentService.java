package pkrause.proj3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkrause.proj3.domain.Student;
import pkrause.proj3.repository.ReactiveStudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@Service
public class ReactiveStudentService implements IReactiveService<Student, UUID> {
    private ReactiveStudentRepository repository;

    @Autowired
    public ReactiveStudentService(ReactiveStudentRepository repository) {
        this.repository = repository;
    }

    public Mono<Student> save(@Valid Student student) {
        return this.repository.save(student);
    }

    public Mono<Student> read(UUID id) {
        return this.repository.findById(id);
    }

    public Flux<Student> read() {
        return this.repository.findAll();
    }

    public Mono<Student> update(UUID id, Student student) {
        student.setId(id);
        return this.repository.save(student);
    }

    public Mono<Void> delete(UUID id) {
        return this.repository.deleteById(id);
    }
}