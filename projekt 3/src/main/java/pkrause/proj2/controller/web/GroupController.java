package pkrause.proj2.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pkrause.proj2.domain.Group;
import pkrause.proj2.result.SingleResult;
import pkrause.proj2.service.GroupService;

@Controller("groupWebController")
public class GroupController {
    private GroupService service;

    @Autowired
    public GroupController(GroupService groupService) {
        this.service = groupService;
    }

    @GetMapping("/group")
    public String allGroup(Model model) {
        model.addAttribute("groups", this.service.read().getResult());

        return "group/all";
    }

    @PostMapping("/group")
    public String addGroup(Group group, Model model) {
        SingleResult<Group> addResult = this.service.save(group);
        if (!addResult.success()) {
            model.addAttribute("errorMsg", addResult.getMessage());

        } else {
            model.addAttribute("msg", "Group with id: " + group.getId() + " has been added");
        }

        model.addAttribute("groups", this.service.read().getResult());

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

            SingleResult<Group> findResult = this.service.read(longId);
            if (!findResult.success()) {
                model.addAttribute("errorMsg", findResult.getMessage());
                return "group/all";
            }

            model.addAttribute("group", findResult.getResult());
            return "group/edit";
        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given uuid");
        }

        return "group/all";
    }

    @PostMapping("/group/edit/")
    public String putGroup(Group group, Model model) {
        SingleResult<Group> updateResult = this.service.update(group.getId(), group);

        if (!updateResult.success()) {
            model.addAttribute("errorMsg", updateResult.getMessage());
        } else {
            model.addAttribute("msg", "Group with id: " + group.getId() + " has been updated");
        }

        model.addAttribute("groups", this.service.read().getResult());

        return "group/all";
    }

    @PostMapping("/group/remove/{id}")
    public String deleteGroup(@PathVariable("id") String id, Model model) {
        try {
            Long longId = Long.valueOf(id);

            SingleResult<Group> deleteResult = this.service.delete(longId);
            if (!deleteResult.success()) {
                model.addAttribute("errorMsg", deleteResult.getMessage());
            } else {
                model.addAttribute("msg", "Group id: " + id + " has been deleted");
            }

        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given longId");
        }

        model.addAttribute("groups", this.service.read().getResult());

        return "group/all";
    }
}
