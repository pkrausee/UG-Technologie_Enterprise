package pkrause.proj3.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Group postGroup(@RequestBody Group group) {
        return this.service.save(group);
    }

    @GetMapping("/api/group")
    public List<Group> getGroup() {
        return this.service.read();
    }

    @GetMapping("/api/group/{id}")
    public Group getGroup(@PathVariable Long id) {
        return this.service.read(id);
    }

    @PutMapping("/api/group/{id}")
    public Group putGroup(@PathVariable Long id, @RequestBody Group group) {
        return this.service.update(id, group);
    }

    @DeleteMapping("/api/group/{id}")
    public void deleteGroup(@PathVariable Long id) {
        this.service.delete(id);
    }
}
