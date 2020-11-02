package com.zdd.java8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FunctionTest {
    public static void main(String[] args) {
        Function<String, Integer> func = x -> x.length();

        Integer apply = func.apply("mkyong");   // 6

        System.out.println(apply);

        Function<String, Integer> s = Integer::parseInt;
        Integer i = s.apply("10");
        System.out.println(i);

//        Comparator<Integer> comparator = Integer::compare;
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        int result = comparator.compare(100, 10);

        System.out.println(result);

        IntBinaryOperator intBinaryOperator = Integer::compare;
        result = intBinaryOperator.applyAsInt(10, 100);
        System.out.println(result);

//        Comparator<Map<String, Long>> priority =(x,y) -> Long.compare(x.getOrDefault("priority", Long.MAX_VALUE),y.getOrDefault("priority", Long.MAX_VALUE))
        Comparator<Map<String, Long>> priority = Comparator.comparing(x -> x.getOrDefault("priority", Long.MAX_VALUE));

        KiteFunction<LocalDateTime, String, String> functionDateFormat = FunctionTest::DateFormat;
        String dateString = functionDateFormat.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateString);

        Stream<String> a = Stream.of("a", "b", "c");
        List<String> list = a.peek(e -> System.out.println(e.toUpperCase())).collect(Collectors.toList());
//        List<String> list = a.forEach(e->System.out.println(e.toUpperCase())).collect(Collectors.toList());
        Stream<String> b = Stream.of("a", "c", "b");
        b.sorted().forEach(e -> System.out.println(e));
//        filter();
//        map();
//        mapToInt();
        Map<Integer, String> map = Maps.newHashMap();
        map.put(1,"1,2,3,4,5");
        map.put(2,"6,7,8,9,10");
        Set<Integer> integers = map.keySet();
        List<String> collect = integers.stream().map(x -> map.get(x)).flatMap(x -> Arrays.stream(x.split("，"))).collect(Collectors.toList());
        System.out.println(collect);
        List<Stream<String>> collect1 = integers.stream().map(x -> Arrays.stream(map.get(x).split("，"))).collect(Collectors.toList());
        System.out.println(collect1);
        collect1.forEach(x -> x.forEach(System.out::println));
        Stream<String> stringStream = integers.stream().map(x -> map.get(x)).flatMap(x -> Arrays.stream(x.split("，")));
        System.out.println(stringStream.count());
//        stringStream.forEach(System.out::println);
//        IntStream intStream = stringStream.flatMapToInt(x -> IntStream.of(Integer.parseInt(x)));
//        intStream.forEach(System.out::println);
        List<String> list2 = Arrays.asList("Geeks", "GFG",
                "GeeksforGeeks", "gfg");

        // Using Stream flatMapToInt(Function mapper)
        // to get length of all strings present in list
        list2.stream().flatMapToInt(str -> IntStream.of(str.length())).forEach(System.out::println);
        System.out.println("=++++++++++++++++");
        list2.stream().mapToInt(str -> str.length()).forEach(System.out::println);


        System.out.println("=++++++++++++++++");
        System.out.println(map);
        flatMap2(map);
        System.out.println(map);
    }
    private static void flatMap2(Map<Integer, String> map){
        map.put(3,"45,6,6,7");
        System.out.println(map);
    }

    private static void flatMap(){
        List<User> users = getUserData();
        List<User> users1 = getUserData();
        List<List<User>> userList = new ArrayList<>();
        userList.add(users);
        userList.add(users1);
        Stream<List<User>> stream = userList.stream();
        List<UserDto> userDtos = stream.flatMap(subUserList->subUserList.stream()).map(user -> dao2Dto(user)).collect(Collectors.toList());
    }



    private static void map(){
        List<User> users = getUserData();
        Stream<User> stream = users.stream();
        List<UserDto> userDtos = stream.map(user -> dao2Dto(user)).collect(Collectors.toList());
        System.out.println(userDtos);
    }
    private static void mapToInt(){
        List<User> users = getUserData();
        Stream<User> stream = users.stream();
        List<User> users2 = Lists.newArrayList();
        for (User user : users) {
            if (user.getUserId() ==4){
                users2.add(0,user);
            }
            if (user.getUserId() ==3){
                users2.add(1,user);
            }
            if (user.getUserId() ==2){
                users2.add(2,user);
            }
            if (user.getUserId() ==6){
                users2.add(3,user);
            }
            if (user.getUserId() ==1){
                users2.add(4,user);
            }
            if (user.getUserId() ==5){
                users2.add(5,user);
            }
        }
        List<Integer> seq = Lists.newArrayList(4, 3, 2, 6, 1, 5);
        for (Integer i : seq){
            users2.add(users.stream().filter(user -> user.getUserId() == i).findFirst().get());
        }

        System.out.println(  stream.mapToInt(User::getAge).boxed().collect(Collectors.toList()));
    }



    private static UserDto dao2Dto(User user){
        UserDto dto = new UserDto();
        try {
            BeanUtils.copyProperties(user, dto);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //其他额外处理
        return dto;
    }


    public static String DateFormat(LocalDateTime dateTime, String partten) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(partten);
        return dateTime.format(dateTimeFormatter);
    }

    private static List<User> getUserData() {
        Random random = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName(String.format("古时的风筝 %s 号", i));
            user.setAge(random.nextInt(100));
            user.setGender(i % 2);
            user.setPhone("18812021111");
            user.setAddress("无");
            users.add(user);
        }
        return users;
    }


    private static void filter() {
        List<User> users = getUserData();
        Stream<User> stream = users.stream();
        stream.filter(user -> user.getGender().equals(0) && user.getAge() > 50).forEach(e -> System.out.println(e));

        /**
         *等同于下面这种形式 匿名内部类
         */
//    stream.filter(new Predicate<User>() {
//        @Override
//        public boolean test(User user) {
//            return user.getGender().equals(0) && user.getAge()>50;
//        }
//    }).forEach(e->System.out.println(e));
    }


    @Data
    private static class User {
        int userId;
        String userName;
        int age;
        Integer gender;
        String phone;
        String address;
    }

    @Data
    private static class UserDto {
        int userId;
        String userName;
        int age;
        Integer gender;
        String phone;
        String address;
    }

}
