package pl.tjeZad1.zad2.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {
    private String content;

    @Autowired
    private GoodByeWorld goodByeWorld;

    public HelloWorld()
    {
        this.content = "Autowired";
    }

    public HelloWorld(GoodByeWorld goodByeWorld)
    {
        this.content = "hello";
        this.goodByeWorld = goodByeWorld;
    }

    @Override
    public String toString() {
        return "HelloWorld \n" +
                "\t content= " + content + "\n" +
                "\t GoodByeWorld= " + goodByeWorld;
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
}
