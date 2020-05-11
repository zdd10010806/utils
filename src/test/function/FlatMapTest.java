package function;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapTest {

    @Test
    public void test01() {
        List<String> words = new ArrayList<String>();
        words.add("your");
        words.add("name");


        Stream<Stream<Character>> result = words.stream().map(w -> characterStream(w));
        result.forEach(x -> x.forEach(System.out::print));

        System.out.println();

        Stream<Character> letters = words.stream().flatMap(w -> characterStream(w));
        letters.forEach(System.out::print);

        System.out.println();

        List<String> stringList = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(Collectors.toList());
        stringList.forEach(e -> System.out.println(e));
    }


    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray())
            result.add(c);
        return result.stream();
    }
}
