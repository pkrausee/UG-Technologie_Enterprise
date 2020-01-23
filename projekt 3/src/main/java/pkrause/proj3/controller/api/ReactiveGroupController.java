package pkrause.proj3.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pkrause.proj3.domain.MongoGroup;
import pkrause.proj3.service.ReactiveGroupService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveGroupController {
    private ReactiveGroupService service;

    @Autowired
    public ReactiveGroupController(ReactiveGroupService service) {
        this.service = service;
    }

    @PostMapping("/api/reactive/group")
    public Mono<MongoGroup> postStudent(@RequestBody MongoGroup mongoGroup) {
        return this.service.save(mongoGroup);
    }

    @GetMapping("/api/reactive/group")
    public Flux<MongoGroup> getStudent() {
        return this.service.read();
    }

    @GetMapping("/api/reactive/group/{id}")
    public Mono<MongoGroup> getStudent(@PathVariable Long id) {
        return this.service.read(id);
    }

    @PutMapping("/api/reactive/group/{id}")
    public Mono<MongoGroup> putStudent(@PathVariable Long id, @RequestBody MongoGroup mongoGroup) {
        return this.service.update(id, mongoGroup);
    }

    @DeleteMapping("/api/reactive/group/{id}")
    public Mono<Void> deleteStudent(@PathVariable Long id) {
        return this.service.delete(id);
    }
}
