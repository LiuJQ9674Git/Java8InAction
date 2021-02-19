package lambdasinaction.chap4;

import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;


public class StreamVsCollection {

    public static void main(String...args){
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> s = names.stream();
        s.forEach(System.out::println);
        //Java8
        //Lambdas
        //In
        //Action
        
        //Exception in thread "main" java.lang.IllegalStateException:
        // stream has already been operated upon or closed
        // 只能变量一次，第二次抛出异常
        //s.forEach(System.out::println);
    }
}