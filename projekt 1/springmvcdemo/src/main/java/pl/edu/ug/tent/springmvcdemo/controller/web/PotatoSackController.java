package pl.edu.ug.tent.springmvcdemo.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.ug.tent.springmvcdemo.domain.PotatoSack;
import pl.edu.ug.tent.springmvcdemo.result.SingleResult;
import pl.edu.ug.tent.springmvcdemo.service.PotatoSackManager;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller("potatoSackWebController")
public class PotatoSackController {
    private PotatoSackManager potatoSackManager;

    @Autowired
    public PotatoSackController(PotatoSackManager potatoSackManager) {
        this.potatoSackManager = potatoSackManager;
    }

    @GetMapping("")
    public String home(Model model) {
        return allPotatoSack(model);
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/potatoSack")
    public String allPotatoSack(Model model) {
        List<PotatoSack> result = this.potatoSackManager.getAllPotatoSacks().getResult();
        model.addAttribute("potatoSacks", result);

        return "potatoSack-all";
    }

    @GetMapping("/potatoSack/magazine")
    public String allByMagazinePotatoSack(Model model) {
        Map<String, Integer> result = this.potatoSackManager.getAllPotatoSacksByMagazine().getResult();
        model.addAttribute("potatoSacks", result);

        return "potatoSack-all-magazine";
    }

    @GetMapping("/potatoSack/kind")
    public String allByKindPotatoSack(Model model) {
        Map<String, Integer> result = this.potatoSackManager.getAllPotatoSacksByKind().getResult();
        model.addAttribute("potatoSacks", result);

        return "potatoSack-all-kind";
    }

    @GetMapping("/potatoSack/magazine/{magazine}")
    public String allByMagazinePotatoSack(@PathVariable("magazine") String magazine, Model model) {
        List<PotatoSack> result = this.potatoSackManager.findByMagazine(magazine).getResult();
        model.addAttribute("potatoSacks", result);

        return "potatoSack-all";
    }

    @GetMapping("/potatoSack/kind/{kind}")
    public String allByKindPotatoSack(@PathVariable("kind") String kind, Model model) {
        List<PotatoSack> result = this.potatoSackManager.findByKind(kind).getResult();
        model.addAttribute("potatoSacks", result);

        return "potatoSack-all";
    }

    @GetMapping("/potatoSack/new")
    public String newPotatoSack(Model model) {
        model.addAttribute("potatoSack", new PotatoSack());

        return "potatoSack-add";
    }

    @PostMapping("/potatoSack")
    public String addPotatoSack(PotatoSack potatoSack, Model model) {
        SingleResult<PotatoSack> addResult = this.potatoSackManager.addPotatoSack(potatoSack);
        if(!addResult.getSuccess()) {
            model.addAttribute("errorMsg", addResult.getMessage());

        } else {
            model.addAttribute("msg", "Sack with id: " + potatoSack.getId() + " has been added");
        }

        List<PotatoSack> result = this.potatoSackManager.getAllPotatoSacks().getResult();
        model.addAttribute("potatoSacks", result);

        return "potatoSack-all";
    }

    @PostMapping("/potatoSack/edit/")
    public String putPotatoSack(PotatoSack potatoSack, Model model) {
        SingleResult<PotatoSack> updateResult = this.potatoSackManager.update(potatoSack.getId(), potatoSack);
        if (!updateResult.getSuccess()) {
            model.addAttribute("errorMsg", updateResult.getMessage());
        } else {
            model.addAttribute("msg", "Sack with id: " + potatoSack.getId() + " has been updated");
        }

        List<PotatoSack> result = this.potatoSackManager.getAllPotatoSacks().getResult();
        model.addAttribute("potatoSacks", result);

        return "potatoSack-all";
    }

    @GetMapping("/potatoSack/edit/{id}")
    public String putPotatoSack(@PathVariable("id") String id, Model model) {
        try {
            UUID uuid = UUID.fromString(id);

            SingleResult<PotatoSack> findResult = this.potatoSackManager.findById(uuid);
            if(!findResult.getSuccess()) {
                model.addAttribute("errorMsg", findResult.getMessage());
                return "potatoSack-all";
            }

            model.addAttribute("potatoSack", findResult.getResult());
            return "potatoSack-edit";
        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given uuid");
        }

        return "potatoSack-all";
    }

    @PostMapping("/potatoSack/remove/{id}")
    public String deletePotatoSack(@PathVariable("id") String id, Model model) {
        try {
            UUID uuid = UUID.fromString(id);

            SingleResult<PotatoSack> deleteResult = this.potatoSackManager.remove(uuid);
            if(!deleteResult.getSuccess()) {
                model.addAttribute("errorMsg", deleteResult.getMessage());
            } else {
                model.addAttribute("msg", "Sack with id: " + id + " has been deleted");
            }

        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given uuid");
        }

        List<PotatoSack> result = this.potatoSackManager.getAllPotatoSacks().getResult();
        model.addAttribute("potatoSacks", result);

        return "potatoSack-all";
    }
}
