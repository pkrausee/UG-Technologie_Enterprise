package pkrause.proj3.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pkrause.proj3.domain.Group;
import pkrause.proj3.domain.Student;
import pkrause.proj3.service.GroupService;
import pkrause.proj3.service.StudentService;

@Controller
public class SetUpController {
    private StudentService studentService;
    private GroupService groupService;

    @Autowired
    public SetUpController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/api/setup")
    public ResponseEntity<String> setUp() {

        Group group1 = new Group();
        Group group2 = new Group();
        group1.setName("Group 1");
        group2.setName("Group 2");
        group1.setSpecialization("Spec 1");
        group2.setSpecialization("Spec 2");
        group1.setMaxStudents(20);
        group2.setMaxStudents(20);

        if (!groupService.save(group1).success() || !groupService.save(group2).success()) {
            return new ResponseEntity<>("Group error!", HttpStatus.BAD_REQUEST);
        }

        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Student student4 = new Student();
        student1.setName("Student 1");
        student2.setName("Student 1");
        student3.setName("Student 1");
        student4.setName("Student 1");
        student1.setSurname("Surname 1");
        student2.setSurname("Surname 1");
        student3.setSurname("Surname 1");
        student4.setSurname("Surname 1");
        student1.setGroup(group1);
        student2.setGroup(group1);
        student3.setGroup(group2);
        student4.setGroup(group2);

        if (
                !studentService.save(student1).success() ||
                        !studentService.save(student2).success() ||
                        !studentService.save(student3).success() ||
                        !studentService.save(student4).success()) {
            return new ResponseEntity<>("Student error!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
