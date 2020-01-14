package pkrause.proj2.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pkrause.proj2.domain.Lecture;
import pkrause.proj2.result.MultipleResult;
import pkrause.proj2.result.SingleResult;
import pkrause.proj2.service.LectureService;

import java.util.Collections;
import java.util.List;

@RestController
public class LectureController {
    private LectureService service;

    @Autowired
    public LectureController(LectureService service) {
        this.service = service;
    }

    @PostMapping("/api/lecture")
    public ResponseEntity<Lecture> postLecture(RequestEntity<Lecture> lecture) {
        if (lecture.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        SingleResult<Lecture> saveResult = this.service.save(lecture.getBody());

        if (saveResult.success()) {
            return new ResponseEntity<>(lecture.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(lecture.getBody(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/lecture")
    public ResponseEntity<List<Lecture>> getLecture() {
        MultipleResult<Lecture> readResult = this.service.read();

        if (readResult.success()) {
            return new ResponseEntity<>(readResult.getResult(), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/lecture/{id}")
    public ResponseEntity<Lecture> getLecture(@PathVariable String id) {
        try {
            Long longId = Long.valueOf(id);

            SingleResult<Lecture> readResult = this.service.read(longId);

            if (readResult.success()) {
                return new ResponseEntity<>(readResult.getResult(), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/lecture/{id}")
    public ResponseEntity<Lecture> putLecture(@PathVariable String id, RequestEntity<Lecture> lecture) {
        if (lecture.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Long longId = Long.valueOf(id);

            SingleResult<Lecture> updateResult = this.service.update(longId, lecture.getBody());

            if (updateResult.success()) {
                return new ResponseEntity<>(updateResult.getResult(), HttpStatus.OK);
            }

            return new ResponseEntity<>(lecture.getBody(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(lecture.getBody(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/lecture/{id}")
    public ResponseEntity<Lecture> deleteLecture(@PathVariable String id) {
        try {
            Long longId = Long.valueOf(id);

            SingleResult<Lecture> deleteResult = this.service.delete(longId);

            if (deleteResult.success()) {
                return new ResponseEntity<>(deleteResult.getResult(), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
