package pl.tjeZad1.zad;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @GetMapping("/hello-world")
    @ResponseBody
    public HelloWorld sayHello() {
        return new HelloWorld("Witam witam");
    }

}
