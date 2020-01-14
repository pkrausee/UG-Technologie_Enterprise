package pkrause.proj2.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pkrause.proj2.domain.Address;
import pkrause.proj2.domain.Group;
import pkrause.proj2.domain.Lecture;
import pkrause.proj2.domain.Student;
import pkrause.proj2.service.AddressService;
import pkrause.proj2.service.GroupService;
import pkrause.proj2.service.LectureService;
import pkrause.proj2.service.StudentService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class SetUpController {
    private StudentService studentService;
    private AddressService addressService;
    private LectureService lectureService;
    private GroupService groupService;

    @Autowired
    public SetUpController(StudentService studentService, AddressService addressService, LectureService lectureService, GroupService groupService) {
        this.studentService = studentService;
        this.addressService = addressService;
        this.lectureService = lectureService;
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

        Lecture lecture1 = new Lecture();
        Lecture lecture2 = new Lecture();
        lecture1.setName("Lecture 1");
        lecture2.setName("Lecture 2");
        lecture1.setRange("Range 1");
        lecture2.setRange("Range 2");
        lecture1.setMaxStudents(20);
        lecture2.setMaxStudents(20);

        Set<Lecture> lectures = new HashSet<>();
        lectures.add(lecture1);
        lectures.add(lecture2);

        if (!lectureService.save(lecture1).success() || !lectureService.save(lecture2).success()) {
            return new ResponseEntity<>("Lecture error!", HttpStatus.BAD_REQUEST);
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
        student1.setLectures(lectures);
        student2.setLectures(lectures);
        student3.setLectures(lectures);
        student4.setLectures(lectures);

//        student1.setAddress(address1);
//        student2.setAddress(address2);
//        student3.setAddress(address3);
//        student4.setAddress(address4);

        if (
                !studentService.save(student1).success() ||
                        !studentService.save(student2).success() ||
                        !studentService.save(student3).success() ||
                        !studentService.save(student4).success()) {
            return new ResponseEntity<>("Student error!", HttpStatus.BAD_REQUEST);
        }

        Address address1 = new Address();
        Address address2 = new Address();
        Address address3 = new Address();
        Address address4 = new Address();
        address1.setAddress("Street 1");
        address2.setAddress("Street 2");
        address3.setAddress("Street 3");
        address4.setAddress("Street 4");
        address1.setCountry("Poland");
        address2.setCountry("Poland");
        address3.setCountry("Poland");
        address4.setCountry("Poland");
        address1.setPostCode("12-345");
        address2.setPostCode("12-345");
        address3.setPostCode("12-345");
        address4.setPostCode("12-345");

        address1.setStudent(student1);
        address2.setStudent(student2);
        address3.setStudent(student3);
        address4.setStudent(student4);

        if (
                !addressService.save(address1).success() ||
                        !addressService.save(address2).success() ||
                        !addressService.save(address3).success() ||
                        !addressService.save(address4).success()) {
            return new ResponseEntity<>("Address error!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    ;
}
