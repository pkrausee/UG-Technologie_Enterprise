package pkrause.proj3.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Student> postStudent(RequestEntity<Student> student) {
        if (student.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.service.save(student.getBody()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/student")
    public ResponseEntity<List<Student>> getStudent() {
        return new ResponseEntity<>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/api/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);

            return new ResponseEntity<>(this.service.read(uuid), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/student/{id}")
    public ResponseEntity<Student> putStudent(@PathVariable String id, RequestEntity<Student> student) {
        if (student.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            UUID uuid = UUID.fromString(id);

            return new ResponseEntity<>(this.service.update(uuid, student.getBody()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);

            this.service.delete(uuid);

            return new ResponseEntity<>("Student deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }
}
