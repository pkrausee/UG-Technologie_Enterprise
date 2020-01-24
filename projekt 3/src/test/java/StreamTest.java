import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StreamTest {

    @Test
    public void testJavaStream() {
        List<String> strings = Stream.of("a", "b", "c").collect(Collectors.toList());

        System.out.println(strings.size());

        String b = Stream.of("a", "b", "c")
                        .filter(string -> string.equals("b"))
                        .findAny()
                        .orElse(null);

        System.out.println(b);            
    }

    @Test
    public void testSpringReactor() {
        List<String> strings1 = new ArrayList<>();
        List<String> strings2 = new ArrayList<>();

        Flux<String> stringStream = Flux.just("a", "b", "c").map(string -> string); 
            
        stringStream.subscribe(strings1::add);
        stringStream.subscribe(strings2::add);
 
        System.out.println(strings1.size() + " " + strings2.size());

        Mono<String> b = stringStream.filter(string -> string.equals("b")).single();

        b.subscribe(s -> System.out.println(s));
    }

}