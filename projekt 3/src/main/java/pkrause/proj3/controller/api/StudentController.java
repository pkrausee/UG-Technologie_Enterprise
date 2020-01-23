package pkrause.proj3.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pkrause.proj3.domain.Student;
import pkrause.proj3.service.StudentService;

import java.util.List;
import java.util.UUID;

@RestController
public class StudentController {
    private StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/api/student")
    public Student postGroup(@RequestBody Student student) {
        return this.service.save(student);
    }

    @GetMapping("/api/student")
    public List<Student> getGroup() {
        return this.service.read();
    }

    @GetMapping("/api/student/{id}")
    public Student getGroup(@PathVariable UUID id) {
        return this.service.read(id);
    }

    @PutMapping("/api/student/{id}")
    public Student putGroup(@PathVariable UUID id, @RequestBody Student student) {
        return this.service.update(id, student);
    }

    @DeleteMapping("/api/student/{id}")
    public void deleteGroup(@PathVariable UUID id) {
        this.service.delete(id);
    }
}
