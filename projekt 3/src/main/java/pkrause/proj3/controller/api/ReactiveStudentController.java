package pkrause.proj3.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pkrause.proj3.domain.MongoStudent;
import pkrause.proj3.service.ReactiveStudentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class ReactiveStudentController {
    private ReactiveStudentService service;

    @Autowired
    public ReactiveStudentController(ReactiveStudentService service) {
        this.service = service;
    }

    @PostMapping("/api/reactive/student")
    public Mono<MongoStudent> postStudent(@RequestBody MongoStudent mongoStudent) {
        return this.service.save(mongoStudent);
    }

    @GetMapping("/api/reactive/student")
    public Flux<MongoStudent> getStudent() {
        return this.service.read();
    }

    @GetMapping("/api/reactive/student/{id}")
    public Mono<MongoStudent> getStudent(@PathVariable UUID id) {
        return this.service.read(id);
    }

    @PutMapping("/api/reactive/student/{id}")
    public Mono<MongoStudent> putStudent(@PathVariable UUID id, @RequestBody MongoStudent mongoStudent) {
        return this.service.update(id, mongoStudent);
    }

    @DeleteMapping("/api/reactive/student/{id}")
    public Mono<Void> deleteStudent(@PathVariable UUID id) {
        return this.service.delete(id);
    }
}
