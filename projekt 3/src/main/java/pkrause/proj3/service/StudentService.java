package pkrause.proj3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkrause.proj3.domain.Student;
import pkrause.proj3.repository.StudentRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService implements IService<Student, UUID> {
    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student save(@Valid Student student) {
        return this.repository.save(student);
    }

    public Student read(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    public List<Student> read() {
        return this.repository.findAll();
    }

    public Student update(UUID id, @Valid Student student) {
        student.setId(id);
        return this.repository.save(student);
    }

    public void delete(UUID id) {
        this.repository.deleteById(id);
    }
}