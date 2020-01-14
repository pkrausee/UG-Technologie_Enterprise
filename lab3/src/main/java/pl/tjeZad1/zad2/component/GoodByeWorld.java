package pl.tjeZad1.zad2.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodByeWorld
{
//    @Autowired
    private String content;

    public GoodByeWorld() {
        content = "Good Bye";
    }

    public GoodByeWorld(String content)
    {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
