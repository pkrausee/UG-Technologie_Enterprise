package pkrause.proj2.controller.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pkrause.proj2.domain.Address;
import pkrause.proj2.result.SingleResult;
import pkrause.proj2.service.AddressService;

import java.util.UUID;

@Controller("addressWebController")
public class AddressController {
    private AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping("/address")
    public String allGroup(Model model) {
        model.addAttribute("addresses", this.service.read().getResult());

        return "address/all";
    }

    @PostMapping("/address")
    public String addLecture(Address address, Model model) {
        SingleResult<Address> addResult = this.service.save(address);

        if (!addResult.success()) {
            model.addAttribute("errorMsg", addResult.getMessage());

        } else {
            model.addAttribute("msg", "Address with id: " + address.getId() + " has been added");
        }

        model.addAttribute("Addresses", this.service.read().getResult());

        return "address/all";
    }

    @GetMapping("/address/new")
    public String newLecture(Model model) {
        model.addAttribute("address", new Address());

        return "address/add";
    }

    @GetMapping("/address/edit/{id}")
    public String putLecture(@PathVariable("id") String id, Model model) {
        try {
            UUID uuid = UUID.fromString(id);

            SingleResult<Address> findResult = this.service.read(uuid);
            if (!findResult.success()) {
                model.addAttribute("errorMsg", findResult.getMessage());
                return "address/all";
            }

            model.addAttribute("address", findResult.getResult());
            return "address/edit";
        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given uuid");
        }

        return "address/all";
    }

    @PostMapping("/address/edit/")
    public String putLecture(Address address, Model model) {
        SingleResult<Address> updateResult = this.service.update(address.getId(), address);

        if (!updateResult.success()) {
            model.addAttribute("errorMsg", updateResult.getMessage());
        } else {
            model.addAttribute("msg", "Address with id: " + address.getId() + " has been updated");
        }

        model.addAttribute("addresses", this.service.read().getResult());

        return "address/all";
    }

    @PostMapping("/address/remove/{id}")
    public String deleteLecture(@PathVariable("id") String id, Model model) {
        try {
            UUID uuid = UUID.fromString(id);

            SingleResult<Address> deleteResult = this.service.delete(uuid);

            if (!deleteResult.success()) {
                model.addAttribute("errorMsg", deleteResult.getMessage());
            } else {
                model.addAttribute("msg", "Address id: " + id + " has been deleted");
            }

        } catch (IllegalArgumentException exception) {
            model.addAttribute("errorMsg", "Cannot parse given longId");
        }

        model.addAttribute("addresses", this.service.read().getResult());

        return "address/all";
    }
}
