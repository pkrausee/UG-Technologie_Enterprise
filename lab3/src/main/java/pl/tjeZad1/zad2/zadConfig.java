package pl.tjeZad1.zad2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.tjeZad1.zad2.component.GoodByeWorld;
import pl.tjeZad1.zad2.component.HelloWorld;

@Configuration
@ComponentScan("pl.tjeZad1.zad2.component")
public class zadConfig {

    @Bean("helloWorld")
    public HelloWorld helloWorld() {
        return new HelloWorld("Hello Bean");
    }

    @Bean("goodByeWorld")
    public GoodByeWorld goodByeWorld() {
        return new GoodByeWorld("Good Bye Bean");
    }

    @Bean
    public String bean() {
        return "Bean";
    }

}
