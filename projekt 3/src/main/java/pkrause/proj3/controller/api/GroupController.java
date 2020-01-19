package pkrause.proj3.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pkrause.proj3.domain.Group;
import pkrause.proj3.service.GroupService;

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

        return new ResponseEntity<>(this.service.save(group.getBody()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/group")
    public ResponseEntity<List<Group>> getGroup() {
        return new ResponseEntity<>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/api/group/{id}")
    public ResponseEntity<Group> getGroup(@PathVariable String id) {
        try {
            Long longId = Long.valueOf(id);

            return new ResponseEntity<>(this.service.read(longId), HttpStatus.OK);
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

            return new ResponseEntity<>(this.service.update(longId, group.getBody()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/group/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable String id) {
        try {
            Long longId = Long.valueOf(id);

            this.service.delete(longId);

            return new ResponseEntity<>("Group deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }
}
