import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StreamPractice {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("aaa", "f");
        map.put("bbb", "t");
        map.put("ccc", "tt");
        map.put("ddd", "f");
        map.put("eee", "fff");

        long count = map.keySet().stream()
                .filter(t -> t.equals("first"))
                .count();


        map.keySet().stream().sorted()
                .skip(1)
                .limit(5)
                .filter(s -> !s.equals("ccc"))
                .forEach(System.out::println);



    }
}
