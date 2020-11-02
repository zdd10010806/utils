package function;

import com.zdd.function.A;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class StreamTest {

    @Test
    public void test01ToMap() {
        int count = 10;
        // 生成 key 的方式
        Function<Long, String> longStringFunction = i -> UUID.randomUUID().toString();
        // 生成 value 的方式 Function.identity()  是对象本身
        Function<Long, Long> identity = Function.identity();
        // 如果key相同的处理方式
        BinaryOperator<Long> longBinaryOperator = (o1, o2) -> o1;


        BinaryOperator<Long> longBinaryOperator2 = (s1, s2) -> {

            //这里使用compareTo 方法 s1>s2 会返回1,s1==s2 返回0 ，否则返回-1
            if (s1.compareTo(s2) < -1) {
                return s2;
            } else {
                return s1;
            }
        };

        BinaryOperator<Long> longBinaryOperator3 = BinaryOperator.maxBy(Comparator.comparing(x -> x));


        Supplier<ConcurrentHashMap<String, Long>> aNew = ConcurrentHashMap::new;
        ConcurrentHashMap<String, Long> collect = LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(longStringFunction, identity, longBinaryOperator3, aNew));
        System.out.println(collect);

        System.out.println("+++++++");
        ConcurrentMap<String, Long> collect1 = LongStream.rangeClosed(10, 15)
                .boxed()
                .collect(Collectors.toConcurrentMap(longStringFunction, Function.identity()));
        System.out.println(collect1);

    }


    @Test
    public void test02() {
        ArrayList<Integer> collect = Stream.of(1, 2, 3, 4, 5, 6, 8, 9, 0).collect(Collectors.toCollection(ArrayList::new));

        List<Integer> collect2 = Stream.of(1, 2, 3, 4, 5, 6, 8, 9, 0).collect(toList());

        //Set
        HashSet<Integer> collect1 = Stream.of(1, 2, 3, 4, 5, 6, 8, 9, 0).collect(Collectors.toCollection(HashSet::new));
        Set<Integer> collect3 = Stream.of(1, 2, 3, 4, 5, 6, 8, 9, 0).collect(Collectors.toSet());


    }

    /**
     * 聚合归约
     */
    @Test
    public void testJoin() {
        String collect = Stream.of(0, 1, 2, 3, 4, 5, 6, 8, 9).map(String::valueOf).collect(Collectors.joining(" * ", "[", "]"));
        System.out.println(collect);

        //Collectors.counting() 统计元素个数，这个和Stream.count() 作用都是一样的，返回的类型一个是包装Long，另一个是基本long
        Long collect1 = Stream.of(1, 0, -10, 9, 8, 100, 200, -80).collect(Collectors.counting());
        System.out.println(collect1);

        //如果仅仅只是为了统计，那就没必要使用Collectors了，那样更消耗资源
        // long 8
        long count = Stream.of(1, 0, -10, 9, 8, 100, 200, -80).count();
        System.out.println(count);
    }

    //Collectors.minBy()、Collectors.maxBy() 和Stream.min()、Stream.max() 作用也是一样的，只不过Collectors.minBy()、Collectors.maxBy()适用于高级场景

    @Test
    public void testMinMax() {
        // maxBy 200
        Comparator<Integer> compareTo = Integer::compareTo;
        Stream.of(1, 0, -10, 9, 8, 100, 200, -80)
                .collect(Collectors.maxBy(compareTo)).ifPresent(System.out::println);

        // max 200
        Stream.of(1, 0, -10, 9, 8, 100, 200, -80)
                .max(compareTo).ifPresent(System.out::println);

        // minBy -80
        Stream.of(1, 0, -10, 9, 8, 100, 200, -80)
                .collect(Collectors.minBy(compareTo)).ifPresent(System.out::println);

        // min -80
        Stream.of(1, 0, -10, 9, 8, 100, 200, -80)
                .min(compareTo).ifPresent(System.out::println);

        System.out.println("++++");
        System.out.println(Stream.of(1, 0, -10, 9, 8, 100, 200, -80).map(Long::valueOf).min(Long::compareTo).get());

    }

    //Collectors.summingInt() 返回的是一个SummaryStatistics(求总)，包含了数量统计count、求和sum、最小值min、平均值average、最大值max。
    @Test
    public void testSumming() {
        //IntSummaryStatistics{count=10, sum=55, min=1, average=5.500000, max=10}
        IntSummaryStatistics collect = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .collect(Collectors.summarizingInt(Integer::valueOf));

        System.out.println(collect);

        //DoubleSummaryStatistics{count=10, sum=55.000000, min=1.000000, average=5.500000, max=10.000000}
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .collect(Collectors.summarizingDouble(Double::valueOf));

        //LongSummaryStatistics{count=10, sum=55, min=1, average=5.500000, max=10}
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .collect(Collectors.summarizingLong(Long::valueOf));


        // 55
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).mapToInt(Integer::valueOf).sum();

        // 55.0
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).mapToDouble(Double::valueOf).sum();

        // 55
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).mapToLong(Long::valueOf).sum();

        //Collectors.averagingInt()   求平均值
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.averagingInt(Integer::valueOf)));

        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.averagingDouble(Double::valueOf)));

        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.averagingLong(Long::valueOf)));

    }

    //Collectors.reducing() 好像也和Stream.reduce()

    @Test
    public void testReduce() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.reducing(Integer::sum)).ifPresent(System.out::println);
        BinaryOperator<Integer> sum = Integer::sum;
        BinaryOperator<Integer> sum2 = (x1, x2) -> x1 + x2;
        Collector<Integer, ?, Optional<Integer>> reducing = Collectors.reducing(sum);
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(reducing).ifPresent(System.out::println);
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.reducing(0, (i1, i2) -> i1 + i2)));

    }

    //group
    //Collectors.partitioningBy() 最多只能将数据分为两部分，Predicate只会有true 和false 两种结果，所有partitioningBy最多只能将数据分为两组

    @Test
    public void testGroup() {
        //Map<String,List<Integer>>
        Function<Integer, String> integerStringFunction = integer -> {
            if (integer < 0) {
                return "小于";
            } else if (integer == 0) {
                return "等于";
            } else {
                return "大于";
            }
        };

        Map<String, List<Integer>> collect = Stream.of(-6, -7, -8, -9, 1, 2, 3, 4, 5, 6, 0, 0)
                .collect(Collectors.groupingBy(integerStringFunction));
        System.out.println(collect);

        //Map<String,Set<Integer>>
        //自定义下游收集器
        Map<String, Set<Integer>> collect1 = Stream.of(-6, -7, -8, -9, 1, 2, 3, 4, 5, 6, 0, 0).collect(Collectors.groupingBy(integerStringFunction, Collectors.toSet()));
        System.out.println(collect1);


        //Map<String,Set<Integer>>
        //自定义map容器 和 下游收集器
        Stream.of(-6, -7, -8, -9, 1, 2, 3, 4, 5, 6).collect(Collectors.groupingBy(integerStringFunction, LinkedHashMap::new, Collectors.toSet()));


        //Map<Boolean,List<Integer>>
        Map<Boolean, List<Integer>> collect2 = Stream.of(0, 1, 0, 1).collect(Collectors.partitioningBy(integer -> integer == 0));
        System.out.println(collect2);

        //Map<Boolean,Set<Integer>>
        //自定义下游收集器
        Map<Boolean, Set<Integer>> collect3 = Stream.of(0, 1, 0, 1).collect(Collectors.partitioningBy(integer -> integer == 0, Collectors.toSet()));
        System.out.println(collect3);
    }

    //mapping
    @Test
    public void testMapping() {
        //List<String>
        List<Integer> collect = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .collect(Collectors.mapping(x -> x * x, toList()));
        System.out.println(collect);
    }

    //Collectors.collectingAndThen()收集后操作
    @Test
    public void testCollectingAndThen() {
        //listIterator
        ListIterator<Integer> collect = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .collect(Collectors.collectingAndThen(toList(), List::listIterator));
//         .collect(Collectors.collectingAndThen(Collectors.toList(), x -> x.listIterator()));

        while (collect.hasNext()) {
            System.out.println(collect.next());
        }

        Supplier supplier = ConcurrentHashMap::new;
        supplier = () -> new ConcurrentHashMap();
        Consumer consumer = System.out::print;

    }

    @Test
    public void testMap() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> collect = words.stream()
                .map(word -> word.split("")) //  String -> String[]
                .distinct()
                .collect(toList());

        System.out.println(collect);


        List<Stream<String>> streamList = words.stream()
                .map(str -> str.split(""))
                .map(str -> Arrays.stream(str))  //String -> String[] -> Stream<String[]>
                .collect(Collectors.toList());

        System.out.println(streamList);


        List<String> stringList =words.stream().
                        map(str -> str.split("")).     //String -> String[]
                         flatMap(Arrays::stream)  //各个数组并不是分别映射成一个流，而是映射成流的内容，所有使用Arrays.stream(str) 合并，扁平化为一个流。
                         .collect(Collectors.toList());

        System.out.println(stringList);

        //合并多个list 为一个list
        List<String> words2 = Arrays.asList("Hello", "World");
        List<String> words3 = Arrays.asList("Hello", "World");
        List<String> collect1 = Stream.of(words2, words3).flatMap(Collection::stream).collect(Collectors.toList());
        List<String> collect2 = Stream.of(words2, words3).flatMap(u->u.stream()).collect(Collectors.toList());
        System.out.println(collect1);

        String[] strings = {"a", "b"};
        Stream<String> stream = Arrays.stream(strings);
        Stream<String> strings1 = Stream.of(strings);

        List<String> collect3 = IntStream.rangeClosed(1, 3).mapToObj(i -> "item" + ThreadLocalRandom.current().nextInt(3))
                .collect(Collectors.toList());

        System.out.println(collect3);



        //原始类型特化流，分别是IntStream、DoubleStream和LongStream。map方法只能为流中的每个元素返回另一个int，mapToObj这个方法返回一个对象值流
       //Stream类包括IntStream，LongStream，DoubleStream等。可以在流操作中使用原始类型
        // 否则，必须使用Stream<Integer>或Stream<Double>，这会将值装箱。
//        在IntStream中，map方法采用IntUnaryOperator，它将一个int映射到一个int。如果要将流映射到Stream<T>，则必须使用mapToObj
        Stream<int[]> stream1 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

        Stream<Stream<int[]>> streamStream = IntStream.rangeClosed(1, 100).boxed()
                .map(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );


        String s = IntStream.rangeClosed(1, 5)
                .mapToObj( __-> "a").collect(Collectors.joining("")) + UUID.randomUUID().toString();

        System.out.println(s);//aaaaa6666dfc9-eb27-41aa-8b1e-c8f6e187d963



    }




}
