package pkrause.proj2.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pkrause.proj2.domain.Student;
import pkrause.proj2.result.MultipleResult;
import pkrause.proj2.result.SingleResult;
import pkrause.proj2.service.StudentService;

import java.util.Collections;
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

        SingleResult<Student> saveResult = this.service.save(student.getBody());

        if (saveResult.success()) {
            return new ResponseEntity<>(student.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(student.getBody(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/student")
    public ResponseEntity<List<Student>> getStudent() {
        MultipleResult<Student> readResult = this.service.read();

        if (readResult.success()) {
            return new ResponseEntity<>(readResult.getResult(), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);

            SingleResult<Student> readResult = this.service.read(uuid);

            if (readResult.success()) {
                return new ResponseEntity<>(readResult.getResult(), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

            SingleResult<Student> updateResult = this.service.update(uuid, student.getBody());

            if (updateResult.success()) {
                return new ResponseEntity<>(updateResult.getResult(), HttpStatus.CREATED);
            }

            return new ResponseEntity<>(student.getBody(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(student.getBody(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/student/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);

            SingleResult<Student> deleteResult = this.service.delete(uuid);

            if (deleteResult.success()) {
                return new ResponseEntity<>(deleteResult.getResult(), HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
