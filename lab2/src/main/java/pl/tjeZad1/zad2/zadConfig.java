package pl.tjeZad1.zad2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tjeZad1.zad2.component.GoodByeWorld;
import pl.tjeZad1.zad2.component.HelloWorld;

@Configuration
public class zadConfig {

    @Bean("HelloWorld")
    public HelloWorld helloWorld(){
        return new HelloWorld();
    }

    @Bean("GoodByeWorld")
    public GoodByeWorld goodByeWorld() {
        return new GoodByeWorld("bye bye");
    }

}
