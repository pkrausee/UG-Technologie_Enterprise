package pkrause.proj2.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pkrause.proj2.domain.Lecture;
import pkrause.proj2.result.SingleResult;
import pkrause.proj2.service.LectureService;

@Controller("lectureWebController")
public class LectureController {
    private LectureService service;

    @Autowired
    public LectureController(LectureService service) {
        this.service = service;
    }

    @GetMapping("/lecture")
    public String allGroup(Model model) {
        model.addAttribute("lectures", this.service.read().getResult());

        return "lecture/all";
    }

    @PostMapping("/lecture")
    public String addLecture(Lecture lecture, Model model) {
        SingleResult<Lecture> addResult = this.service.save(lecture);
        if (!addResult.success()) {
            model.addAttribute("errorMsg", addResult.getMessage());

        } else {
            model.addAttribute("msg", "Lecture with id: " + lecture.getId() + " has been added");
        }

        model.addAttribute("lectures", this.service.read().getResult());

        return "lecture/all";
    }

    @GetMapping("/lecture/new")
    public String newLecture(Model model) {
        model.addAttribute("lecture", new Lecture());

        return "lecture/add";
    }

    @GetMapping("/lecture/edit/{id}")
    public String putLecture(@PathVariable("id") String id, Model model) {
        try {
            Long longId = Long.valueOf(id);

            SingleResult<Lecture> findResult = this.service.read(longId);
            if (!findResult.success()) {
                model.addAttribute("errorMsg", findResult.getMessage());
                return "lecture/all";
            }

            model.addAttribute("lecture", findResult.getResult());
            return "lecture/edit";
        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given uuid");
        }

        return "lecture/all";
    }

    @PostMapping("/lecture/edit/")
    public String putLecture(Lecture lecture, Model model) {
        SingleResult<Lecture> updateResult = this.service.update(lecture.getId(), lecture);

        if (!updateResult.success()) {
            model.addAttribute("errorMsg", updateResult.getMessage());
        } else {
            model.addAttribute("msg", "Lecture with id: " + lecture.getId() + " has been updated");
        }

        model.addAttribute("lectures", this.service.read().getResult());

        return "lecture/all";
    }

    @PostMapping("/lecture/remove/{id}")
    public String deleteLecture(@PathVariable("id") String id, Model model) {
        try {
            Long longId = Long.valueOf(id);

            SingleResult<Lecture> deleteResult = this.service.delete(longId);
            if (!deleteResult.success()) {
                model.addAttribute("errorMsg", deleteResult.getMessage());
            } else {
                model.addAttribute("msg", "Lecture id: " + id + " has been deleted");
            }

        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given longId");
        }

        model.addAttribute("lectures", this.service.read().getResult());

        return "lecture/all";
    }
}