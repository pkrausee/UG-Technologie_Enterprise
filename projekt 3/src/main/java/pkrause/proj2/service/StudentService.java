package pkrause.proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkrause.proj2.domain.Student;
import pkrause.proj2.repository.StudentRepository;
import pkrause.proj2.result.MultipleResult;
import pkrause.proj2.result.SingleResult;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService implements IService<Student, UUID> {
    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public SingleResult<Student> save(Student student) {
        if (!student.isValid()) {
            return new SingleResult<>(student, "Not valid.", false);
        }

        this.repository.save(student);

        return new SingleResult<>(student, "Student saved.", true);
    }

    public SingleResult<Student> read(UUID id) {
        Student findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(null, "Student not found.", false);
        }

        return new SingleResult<>(findResult, "Success.", true);
    }

    public MultipleResult<Student> read() {
        List<Student> findResult = this.repository.findAll();

        if (findResult.size() == 0) {
            return new MultipleResult<>(findResult, "No students in database", false);
        }

        return new MultipleResult<>(findResult, "Success", true);
    }

    public SingleResult<Student> update(UUID id, Student student) {
        Student findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(null, "Student not found.", false);
        }

        if (!student.isValid()) {
            return new SingleResult<>(student, "Not valid.", false);
        }

        student.setId(id);
        this.repository.save(student);

        return new SingleResult<>(student, "Student updated.", true);
    }

    public SingleResult<Student> delete(UUID id) {
        Student findResult = this.repository.findById(id).orElse(null);

        if (findResult == null) {
            return new SingleResult<>(null, "Student not found.", false);
        }

        try {
            this.repository.deleteById(id);
        } catch (RuntimeException ex) {
            return new SingleResult<>(findResult, "Student cannot be deleted", false);
        }

        return new SingleResult<>(findResult, "Student deleted.", true);
    }
}
