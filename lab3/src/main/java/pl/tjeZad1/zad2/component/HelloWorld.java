package pl.tjeZad1.zad2.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {
    private String content;

    @Autowired
    @Qualifier("helloWorld")
    private HelloWorld world;

    @Autowired
    @Qualifier("goodByeWorld")
    private GoodByeWorld goodByeWorld;

    public HelloWorld()
    {
        this.content = "Hello";
    }

    public HelloWorld(String content)
    {
        this.content = content;
    }

    @Autowired
    public HelloWorld(GoodByeWorld goodByeWorld)
    {
        this.content = "Content";
        this.goodByeWorld = goodByeWorld;
    }

    @Override
    public String toString() {
        return "HelloWorld \n" +
                "\t content = " + content + "\n" +
                "\t goodByeWorld = " + goodByeWorld +
                "\t world = " + world.content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GoodByeWorld getGoodByeWorld() {
        return goodByeWorld;
    }

    public void setGoodByeWorld(GoodByeWorld goodByeWorld) {
        this.goodByeWorld = goodByeWorld;
    }

    public HelloWorld getWorld() {
        return world;
    }

    public void setWorld(HelloWorld world) {
        this.world = world;
    }
}
