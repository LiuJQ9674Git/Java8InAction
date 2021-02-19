package lambdasinaction.chap3;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class Sorting {

    public static void main(String...args){

        // 1
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"),
                new Apple(120, "green")));

        // [Apple{color='green', weight=80},
        // Apple{color='red', weight=120}, Apple{color='green', weight=155}]
        //1 传递代码
        inventory.sort(new AppleComparator());
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(30, "green"));
        
        // 第2步:使用匿名类
        // [Apple{color='green', weight=30},
        // Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2){
                return a1.getWeight().compareTo(a2.getWeight());
        }});
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(20, "red"));
        
        // 第3步:使用Lambda表达式
        // [Apple{color='red', weight=20}, Apple{color='green', weight=30},
        // Apple{color='green', weight=155}]
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(inventory);
        
        // reshuffling things a little
        inventory.set(1, new Apple(10, "red"));
        inventory.set(2, new Apple(10, "green"));
        
        // 第4步:使用方法引用
        // 方法引用 等同于2 Lambdas 直接调用这个方法
        // [Apple{color='red', weight=10}, Apple{color='red', weight=20},
        // Apple{color='green', weight=155}]
        inventory.sort(comparing(Apple::getWeight));
        System.out.println("方法引用 顺序:\t"+inventory);

        //逆序
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight).reversed();
        inventory.sort(c);
        System.out.println("方法引用 逆序:\t"+inventory);

        //重量一样时按照颜色排序
        c=Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor);
        inventory.sort(c);
        System.out.println("方法引用 逆序:\t"+inventory);

        Predicate<Apple> redApple = (Apple apple)->{
            if("red".equals(apple.color)){
                return true;
            }
            return false;
        };

        boolean b=redApple.test(new Apple(120, "red"));
        System.out.println("redApple:\t"+b);

        //产生现有Predicate对象redApple的非
        Predicate<Apple> notRedApple = redApple.negate();
        System.out.println("notRedApple:\t"+
                notRedApple.test(new Apple(120, "red")));

        //链接两个谓词来生成另一个Predicate对象
        //重量大于10
        Predicate<Apple> redAndHeavyApple=redApple.and(a -> a.getWeight() > 10);
        System.out.println("redAndHeavyApple:\t"+
                redAndHeavyApple.test(new Apple(120, "red")));

        //重量大于150或者为红色,要么是绿色
        Predicate<Apple> redAndHeavyAppleOrGreen =redApple.and(a -> a.getWeight() > 150)
                .or(a -> "green".equals(a.getColor()));

        System.out.println("redAndHeavyAppleOrGreen:\t"+
                redAndHeavyAppleOrGreen.test(new Apple(100, "red")));

        System.out.println("redAndHeavyAppleOrGreen:\t"+
                redAndHeavyAppleOrGreen.test(new Apple(160, "red")));

        System.out.println("redAndHeavyAppleOrGreen:\t"+
                redAndHeavyAppleOrGreen.test(new Apple(160, "green")));
        //
        List<Apple> list=filter(inventory,redAndHeavyAppleOrGreen);
        //[Apple{color='green', weight=155}, Apple{color='green', weight=10}]
        System.out.println("list:\t"+
                list);
    }

    //Lambdas表达式
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for(T s: list){
            if(p.test(s)){
                results.add(s);
            }
        }
        return results;
    }

    public static class Apple {
        private Integer weight = 0;
        private String color = "";

        public Apple(Integer weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                   "color='" + color + '\'' +
                   ", weight=" + weight +
                   '}';
        }
    }

    //Comparator为Java接口
    static class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2){
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }
}
