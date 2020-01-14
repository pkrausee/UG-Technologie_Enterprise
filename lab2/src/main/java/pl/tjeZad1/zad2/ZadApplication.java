package pl.tjeZad1.zad2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.jar.JarEntry;


public class ZadApplication {

    public static void main(String[] args) {

//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        ApplicationContext context = new AnnotationConfigApplicationContext(zadConfig.class);

        System.out.println(context.getBean("HelloWorld"));

    }

}
