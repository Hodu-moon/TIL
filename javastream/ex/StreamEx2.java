package ex;

import java.io.File;
import java.util.stream.Stream;

public class StreamEx2 {
    public static void main(String[] args) {
        File[] fileArr = {new File("Ex1.java"), new File("Ex1.bak"), new File("Ex1.txt"), new File("Ex1"), new File("Ex2.java")};

        Stream<File> fileStream = Stream.of(fileArr);

        // 확장자만 따로 빼기

        fileStream.map(File::getName) // Stream<File> -> Stream<String>
                .filter(name -> name.contains(".")) // Stream<String> -> Stream<String>
                .map(name -> name.substring(name.indexOf(".") + 1)) // Stream<String> -> Stream<String>
                .distinct()
                .forEach(System.out::println);

    }
}
