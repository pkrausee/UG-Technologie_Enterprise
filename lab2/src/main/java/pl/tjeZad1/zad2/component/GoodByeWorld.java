package pl.tjeZad1.zad2.component;


import org.springframework.stereotype.Component;

@Component
public class GoodByeWorld
{
    private String content;

    public GoodByeWorld()
    {
        this.content = "Autowired";
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
