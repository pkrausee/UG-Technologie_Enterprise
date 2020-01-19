package pkrause.proj3.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pkrause.proj3.domain.Group;
import pkrause.proj3.service.GroupService;

@Controller("groupWebController")
public class GroupController {
    private GroupService service;

    @Autowired
    public GroupController(GroupService groupService) {
        this.service = groupService;
    }

    @GetMapping("/group")
    public String allGroup(Model model) {
        model.addAttribute("groups", this.service.read());

        return "group/all";
    }

    @PostMapping("/group")
    public String addGroup(Group group, Model model) {
        model.addAttribute("msg", "Group with id: " + this.service.save(group).getId() + " has been added");

        model.addAttribute("groups", this.service.read());

        return "group/all";
    }

    @GetMapping("/group/new")
    public String newGroup(Model model) {
        model.addAttribute("group", new Group());

        return "group/add";
    }

    @GetMapping("/group/edit/{id}")
    public String putGroup(@PathVariable("id") String id, Model model) {
        try {
            Long longId = Long.valueOf(id);

            Group findResult = this.service.read(longId);

            model.addAttribute("group", findResult);
            return "group/edit";
        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given uuid");
        }

        return "group/all";
    }

    @PostMapping("/group/edit/")
    public String putGroup(Group group, Model model) {
        Group updateResult = this.service.update(group.getId(), group);

        model.addAttribute("msg", "Group with id: " + updateResult.getId() + " has been updated");
        model.addAttribute("groups", this.service.read());

        return "group/all";
    }

    @PostMapping("/group/remove/{id}")
    public String deleteGroup(@PathVariable("id") String id, Model model) {
        try {
            Long longId = Long.valueOf(id);

            this.service.delete(longId);

            model.addAttribute("msg", "Group id: " + id + " has been deleted");
        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given longId");
        }

        model.addAttribute("groups", this.service.read());

        return "group/all";
    }
}
