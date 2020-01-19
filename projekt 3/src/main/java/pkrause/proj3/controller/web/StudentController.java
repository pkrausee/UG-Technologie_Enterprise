package pkrause.proj3.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pkrause.proj3.domain.Student;
import pkrause.proj3.service.StudentService;

import java.util.UUID;

@Controller("studentWebController")
public class StudentController {
    private StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/student")
    public String allGroup(Model model) {
        model.addAttribute("students", this.service.read());

        return "student/all";
    }

    @PostMapping("/student")
    public String addLecture(Student student, Model model) {
        model.addAttribute("msg", "Student with id: " + this.service.save(student).getId() + " has been added");
        model.addAttribute("Students", this.service.read());

        return "student/all";
    }

    @GetMapping("/student/new")
    public String newLecture(Model model) {
        model.addAttribute("student", new Student());

        return "student/add";
    }

    @GetMapping("/student/edit/{id}")
    public String putLecture(@PathVariable("id") String id, Model model) {
        try {
            UUID uuid = UUID.fromString(id);
            Student findResult = this.service.read(uuid);

            model.addAttribute("student", findResult);

            return "student/edit";
        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given uuid");
        }

        return "student/all";
    }

    @PostMapping("/student/edit/")
    public String putLecture(Student student, Model model) {
        Student updateResult = this.service.update(student.getId(), student);

        model.addAttribute("msg", "Students with id: " + updateResult.getId() + " has been updated");
        model.addAttribute("students", this.service.read());

        return "student/all";
    }

    @PostMapping("/student/remove/{id}")
    public String deleteLecture(@PathVariable("id") String id, Model model) {
        try {
            UUID uuid = UUID.fromString(id);

            this.service.delete(uuid);

            model.addAttribute("msg", "Students id: " + id + " has been deleted");
        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given longId");
        }

        model.addAttribute("students", this.service.read());

        return "student/all";
    }
}