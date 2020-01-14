package pkrause.proj2.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pkrause.proj2.domain.Group;
import pkrause.proj2.result.MultipleResult;
import pkrause.proj2.result.SingleResult;
import pkrause.proj2.service.GroupService;

import java.util.Collections;
import java.util.List;

@RestController
public class GroupController {
    private GroupService service;

    @Autowired
    public GroupController(GroupService service) {
        this.service = service;
    }

    @PostMapping("/api/group")
    public ResponseEntity<Group> postGroup(RequestEntity<Group> group) {
        if (group.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        SingleResult<Group> saveResult = this.service.save(group.getBody());

        if (saveResult.success()) {
            return new ResponseEntity<>(group.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(group.getBody(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/group")
    public ResponseEntity<List<Group>> getGroup() {
        MultipleResult<Group> readResult = this.service.read();

        if (readResult.success()) {
            return new ResponseEntity<>(readResult.getResult(), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/group/{id}")
    public ResponseEntity<Group> getGroup(@PathVariable String id) {
        try {
            Long longId = Long.valueOf(id);

            SingleResult<Group> readResult = this.service.read(longId);

            if (readResult.success()) {
                return new ResponseEntity<>(readResult.getResult(), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/group/{id}")
    public ResponseEntity<Group> putGroup(@PathVariable String id, RequestEntity<Group> group) {
        if (group.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Long longId = Long.valueOf(id);

            SingleResult<Group> updateResult = this.service.update(longId, group.getBody());

            if (updateResult.success()) {
                return new ResponseEntity<>(updateResult.getResult(), HttpStatus.OK);
            }

            return new ResponseEntity<>(group.getBody(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(group.getBody(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/group/{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable String id) {
        try {
            Long longId = Long.valueOf(id);

            SingleResult<Group> deleteResult = this.service.delete(longId);

            if (deleteResult.success()) {
                return new ResponseEntity<>(deleteResult.getResult(), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
