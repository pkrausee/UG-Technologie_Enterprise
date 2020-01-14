package pkrause.proj2.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("WebController")
public class SchoolController {

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("")
    public String home() {
        return "school";
    }

}
