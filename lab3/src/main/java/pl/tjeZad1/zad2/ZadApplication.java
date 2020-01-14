package pl.tjeZad1.zad2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.tjeZad1.zad2.component.GoodByeWorld;
import pl.tjeZad1.zad2.component.HelloWorld;


public class ZadApplication {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(zadConfig.class);

        HelloWorld helloWorld = context.getBean(HelloWorld.class);
        GoodByeWorld goodByeWorld = (GoodByeWorld) context.getBean("goodByeWorld");
        GoodByeWorld goodByeWorldType = context.getBean(GoodByeWorld.class);

        System.out.println(helloWorld);
        System.out.println(goodByeWorld);
        System.out.println(goodByeWorldType);

    }

}
