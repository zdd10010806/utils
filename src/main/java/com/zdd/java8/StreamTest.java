package com.zdd.java8;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

public class StreamTest {

    private static double calc(List<Integer> ints) {
        //临时中间集合
        List<Point2D> point2DList = new ArrayList<>();
        for (Integer i : ints) {
            point2DList.add(new Point2D.Double((double) i % 3, (double) i / 3));
        }
        //临时变量，纯粹是为了获得最后结果需要的中间变量
        double total = 0;
        int count = 0;

        for (Point2D point2D : point2DList) {
            //过滤
            if (point2D.getY() > 1) {
                //算距离
                double distance = point2D.distance(0, 0);
                total += distance;
                count++;
            }
        }
        //注意count可能为0的可能
        return count > 0 ? total / count : 0;
    }

    public static void main(String[] args) {

//        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
//        double average = calc(ints);
//        double streamResult = ints.stream()
//                .map(i -> new Point2D.Double((double) i % 3, (double) i / 3))
//                .filter(point -> point.getY() > 1)
//                .mapToDouble(point -> point.distance(0, 0))
//                .average()
//                .orElse(0);
//        System.out.println(average);
//        System.out.println(streamResult);
//        System.out.println(Double.compare(average,streamResult));
//
//        Stream.of("one", "two", "three", "four")
//                .peek(e -> System.out.println("Peeked value: " + e))
//                .map(String::toUpperCase)
//                .peek(e -> System.out.println("Mapped value: " + e))
//                .collect(Collectors.toList());
//如何用一行代码来实现，比较一下可读性
//        assertThat(average, is(streamResult));



        List<String> strings = Arrays.asList("AB/CD/EFH", "ABC/CDFG/EFHGG", "A/B/N");

        int asInt = Stream.of( Joiner.on("/").join(strings).split("/")).mapToInt(String::length).max().getAsInt();
        System.out.println(asInt);


        String[] ss = {"AB/CD/EFH", "ABC/CDFG/EFHGG", "A/B/N"};

        Stream<Stream<String>> streamStream = Stream.of(ss).map(word -> Arrays.stream(word.split("/")));

        Stream<String> stringStream = Stream.of(ss).flatMap(word -> Arrays.stream(word.split("/")));
        Stream<String[]> stream = Stream.of(ss).map(word -> word.split("/"));

        int asInt1 = stringStream.mapToInt(String::length).max().getAsInt();
        System.out.println(asInt1);
        int asInt2 = Stream.of(ss).flatMapToInt(word -> Arrays.stream(word.split("/")).mapToInt(String::length)).max().getAsInt();
        System.out.println(asInt2);


    }
}
