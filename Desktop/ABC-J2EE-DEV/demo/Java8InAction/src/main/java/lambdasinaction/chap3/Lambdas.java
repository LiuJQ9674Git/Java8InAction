package lambdasinaction.chap3;

import lambdasinaction.chap14.PatternMatching;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.function.*;

import static java.util.Comparator.comparing;

public class Lambdas {
	public static void main(String ...args){

		// Simple example
		Runnable r = () -> System.out.println("Hello!");
		r.run();

		// Filtering with lambdas
		List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
				new Apple(155, "green"), new Apple(120, "red"));

		// Lambdas表达式形式
		// [Apple{color='green', weight=80},
		// Apple{color='green', weight=155}]
		List<Apple> greenApples = filter(inventory,
				(Apple a) -> "green".equals(a.getColor()));
		System.out.println(greenApples);

		Function<Apple,Integer> appleWeight= (Apple a) -> a.getWeight();
		List<Integer> appleList = map(inventory,appleWeight);
		System.out.println("appleList:\t"+appleList);
		//方法引用
		appleList = map(inventory,Apple::getWeight);
		System.out.println("方法引用：appleList:\t"+appleList);



		Comparator<Apple> c = (Apple a1, Apple a2) ->
				a1.getWeight().compareTo(a2.getWeight());

		// [Apple{color='green', weight=80}, Apple{color='red', weight=120},
		// Apple{color='green', weight=155}]
		inventory.sort(c);
		System.out.println(inventory);

		inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
		System.out.println("Lambdas inventory：\t"+inventory);

		inventory.sort(Comparator.comparing(Apple::getWeight));
		System.out.println("方法引用 inventory：\t"+inventory);

		//java.lang.Exception: Stack trace
		//	at java.lang.Thread.dumpStack(Thread.java:1329)
		//	at lambdasinaction.chap3.Lambdas.lambda$main$5(Lambdas.java:50)
		//	at lambdasinaction.chap3.Lambdas.processNullNull(Lambdas.java:178)
		//	at lambdasinaction.chap3.Lambdas.main(Lambdas.java:51)
		NullNull  nullNull=() -> Thread.currentThread().dumpStack();
		processNullNull(nullNull);

		//java.lang.Exception: Stack trace
		//	at java.lang.Thread.dumpStack(Thread.java:1329)
		//	at lambdasinaction.chap3.Lambdas.processNullNull(Lambdas.java:191)
		//	at lambdasinaction.chap3.Lambdas.main(Lambdas.java:59)
		nullNull=Thread::dumpStack;
		processNullNull(nullNull);

		//
		BiFunction<String,Integer,String> function=(str, i) -> str.substring(i);
		String sub=function.apply("testatest",3);
		System.out.println("sub:\t"+sub);

	    function=String::substring;
	    sub=function.apply("testatest",3);
		System.out.println("方法引用 sub:\t"+sub);

		//布尔表达式
		Predicate<List<String>> predicateList=(List<String> list) -> list.isEmpty();
		Boolean booleanPred=predicateList.test(Arrays.asList("test"));
		System.out.println("booleanPred:\t"+booleanPred);

		//创建对象
		Supplier<Apple> supplierApple=() -> new Apple(85,"greenGreen");
		System.out.println("newApple:\t"+supplierApple.get());

		//消费一个对象
		Consumer<Apple> consumerApple=(Apple a) -> System.out.println(a.getWeight());
		forEach(inventory,consumerApple);

		Consumer<String> consumerString=(String s) -> System.out.println(s);
		consumerString.accept("testtesttest");

		consumerString=System.out::println;
		consumerString.accept("testtesttest");

		List<String> strList = Arrays.asList("a","b","A","B");
		strList.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

		//
		strList.sort(String::compareToIgnoreCase);
		System.out.println("strList:\t"+strList);

		//静态方法
		Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
		stringToInteger = Integer::parseInt;

		//实例方法
		BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
		contains = List::contains;

		//构造方法引用，无参构造
		Supplier<Orange> c1 = Orange::new;
		Orange orange = c1.get();
		//构造方法，无参构造
		c1 = () -> new Orange();
		Orange a1 = c1.get();

		//有两个值构造方法
		BiFunction<Integer,String, Apple> c2 = Apple::new;
		Apple a2 = c2.apply(110,"Black");
		System.out.println("a2:\t"+a2);

		Function<Integer, Apple> functionApple = Apple::new;
		Apple apple=functionApple.apply(20);
		System.out.println("apple:\t"+apple);

		List<Integer> weights = Arrays.asList(7, 3, 4, 10);
		//Function
		List<Apple> apples = mapApple(weights, Apple::new);
		System.out.println("apples:\t"+apples);

		//从一个对象中 选择/提取
		Function<String, Integer> function1=(String s) -> s.length();
		List<Integer> strMap=map(Arrays.asList("lambdas","in","action"),function1);
		System.out.println("strMap:\t"+strMap);

		ToIntFunction<String> toIntFunction=(String s) -> s.length();
		strMap=map(Arrays.asList("lambdas","in-in","action"),function1);
		System.out.println("strMap:\t"+strMap);

		//合并两个值
		IntBinaryOperator intBinaryOperator=(int a, int b) -> a * b;
		System.out.println("intBinaryOperator:\t"+intBinaryOperator.applyAsInt(10,30));

		//比较两个对象
		Comparator<Apple> comparatorApple=(Apple a11, Apple a21) ->
				a11.getWeight().compareTo(a21.getWeight());
		inventory.sort(comparatorApple);
		System.out.println(inventory);
		//比较值
		BiFunction<Apple, Apple, Integer> biFunction=(Apple a11, Apple a21) ->
				a11.getWeight().compareTo(a21.getWeight());
		Integer objectBig=biFunction.apply(new Apple(81,"green"),
				new Apple(84,"green"));
		System.out.println("objectBig:\t"+objectBig);
		//比较值
		ToIntBiFunction<Apple, Apple> toIntBiFunction=(Apple a11, Apple a21) ->
				a11.getWeight().compareTo(a21.getWeight());
		objectBig=toIntBiFunction.applyAsInt(new Apple(81,"green"),
				new Apple(84,"green"));
		System.out.println("objectBig:\t"+objectBig);


		//Lambda表达式
		processNullNull(()->System.out.println("入参出参为空"));
		String str=processNullString(()->"入参为空，返回为串");
		System.out.println("str:\t"+str);

		str=processIntString(10,(i)-> {return "入参出参为空"+i;});
		System.out.println(str);
		str=processIntString(20,(i)->  "入参出参为空"+i);
		System.out.println(str);

		//函数式编程，Function接口
		List<Integer> l = map(Arrays.asList("lambdas","in","action"),
				(String s) -> s.length());
		System.out.println(l);

		//
		IntPredicate evenNumbers = (int i) -> i % 2 == 0;
		System.out.println("evenNumbers:\t"+evenNumbers.test(10));
		//
		Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
		System.out.println("oddNumbers:\t"+oddNumbers.test(11));

		//
		IntConsumer intConsumer=(int i) ->System.out.println(i);
		System.out.println("intConsumer:\t");
		intConsumer.accept(10);

		//
		IntToDoubleFunction intToDoubleFunction=(int i) -> i / 2.0 ;
		System.out.println("intToDoubleFunction:\t"+
				intToDoubleFunction.applyAsDouble(10));

		//
		LongFunction longFunction=(long i) -> i /2 ;
		System.out.println("longFunction:\t"+longFunction.apply(10));

		//
		LongFunction<String> longStrFunction=(long i) -> "result:"+i /2 ;
		System.out.println("longStrFunction:\t"+
				longStrFunction.apply(10));

		Supplier supplierEx=() ->20 ;
		System.out.println("supplierEx:\t"+supplierEx.get());

		Supplier supplier=() ->{ return  10;} ;
		System.out.println("supplier:\t"+supplier.get());

		//List
		// Predicate返回了一个boolean
		List<String> listPredicate=new ArrayList<>();
		Predicate<String> predicate = s -> listPredicate.add(s);
		predicate.test("listPredicate1");
		predicate.test("listPredicate2");
		System.out.println("listPredicate:\t"+listPredicate);

		// Consumer返回了一个void
		List<String> listConsumer=new ArrayList<>();
		Consumer<String> consumer = s -> listConsumer.add(s);
		consumer.accept("listConsumer3");
		consumer.accept("listConsumer4");
		System.out.println("listConsumer:\t"+listConsumer);

		//
		List<String> listOfStrings=new ArrayList<>();
		//在List中增加数据
		listOfStrings.add("test");
		Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
		List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
		System.out.println("nonEmpty:\t"+nonEmpty);

		//Consumer
		forEach(
				Arrays.asList(1,2,3,4,5),
				(Integer i) -> System.out.println(i)
		);

		//
		List<Integer> ll = map(
				Arrays.asList("lambdasLambdas","in-in","actionAction"),
				(String s) -> s.length()
		);
		System.out.println("ll:\t"+ll);

		Predicate<String> nonEmptyPred = (String s) -> !s.isEmpty();
		List<String> nonEmptyList = filter(listOfStrings, nonEmptyPred);
		System.out.println("nonEmptyList:\t"+nonEmptyList);

		//T->boolean
		Predicate<String> predicateStr=String::isEmpty;
		String strEmpty="";
		Boolean b=predicateStr.test(strEmpty);
		System.out.println("predicateStr:\t"+b);
		b=predicateStr.test("test");
		System.out.println("predicateStr:\t"+b);

		IntPredicate intPredicate=(int i)->true;
		b=intPredicate.test(1);
		System.out.println("intPredicate:\t"+b);

		//T->void
		Consumer<String> stringConsumer=(String s)->System.out.println(s);
		stringConsumer.accept("test");

		LongConsumer longConsumer=(long localL)->System.out.println(localL);
		longConsumer.accept(10l);

		//T->R
		Function<Integer,String>  integerStringFunction=integer -> Integer.toString(integer);
		String iStr=integerStringFunction.apply(10);
		System.out.println("iStr:\t"+iStr);

		IntToDoubleFunction toDoubleFunction=(int value) -> ((Integer)value).doubleValue();
		Double d=toDoubleFunction.applyAsDouble(20);
		System.out.println("d:\t"+d);

		//()->T
		Supplier<String> stringSupplier=()->"test";
		System.out.println("stringSupplier:\t"+stringSupplier.get());

		//T->T
		UnaryOperator<String>  unaryOperator=String::trim;
		String strUn=unaryOperator.apply("t ");
		System.out.println("strUn:\t"+strUn+"\tlen:\t"+strUn.length());

		//(T,T)->T
		BinaryOperator<String> binaryOperator=String::concat;
		strUn=binaryOperator.apply("Hello ","World");
		System.out.println("strUn:\t"+strUn+"\tlen:\t"+strUn.length());

		//(L,R)->boolean
		BiPredicate<Integer,String> biPredicate=(Integer pos,String name)->name.length()>pos;
		System.out.println("biPredicate:\t"+biPredicate.test(2,"test"));

		// (T,U)->void
		BiConsumer<Integer,String>  biConsumer=
				(Integer pos,String name)->System.out.println(name+"\t"+pos);
		biConsumer.accept(2,"test");

		//(T,U)->R
		BiFunction<Integer,String,Apple> biFunction1=Apple::new;
		Apple apple1=biFunction1.apply(10,"test");
		System.out.println("apple1:\t"+apple1);

		TriFunction<Integer, Integer, Integer, Color> colorFactory = Color::new;
		Color color=colorFactory.apply(10,10,30);
		System.out.println("color:\t"+color);

		Fruit fruit=giveMeFruit("apple",20);
		System.out.println("fruit:\t"+fruit);

		//函数复合
		Function<Integer, Integer> f = x -> x + 1;
		Function<Integer, Integer> g = x -> x * 2;
		//数学上会写作g(f(x))或(g o f)(x)
		Function<Integer, Integer> h = f.andThen(g);
		int result = h.apply(1);

		//这将返回4
		System.out.println("result:\t"+result);
		//f(g(x))
		Function<Integer, Integer> h1 = f.compose(g);
		int result1 = h1.apply(1);
		System.out.println("result1:\t"+result1);
	}

	static interface TriFunction<S, T, U, R> {
		R apply(S s, T t, U u);
	}

	public static Fruit giveMeFruit(String fruit, Integer weight){
		return map.get(fruit.toLowerCase()).apply(weight);
	}
	static Map<String, Function<Integer, Fruit>> map = new HashMap<>();

	static {
		map.put("apple", Apple::new);
		map.put("orange", Orange::new);
	}

	public static class Color{
		Integer b;
		Integer g;
		Integer c;
		public Color(Integer b,Integer g,Integer c){
			this.b=b;
			this.g=g;
			this.c=c;
		}

		@Override
		public String toString() {
			return "Color{" +
					"b=" + b +
					", g=" + g +
					", c=" + c +
					'}';
		}
	}

	public static List<Apple> mapApple(List<Integer> list,
								  Function<Integer, Apple> f){
		List<Apple> result = new ArrayList<>();
		for(Integer e: list){
			result.add(f.apply(e));
		}
		return result;
	}

	public static <T> List<T> map(List<T> list,
									 Supplier<T> f) {
		List<T> result = new ArrayList<>();
		for(T s: list) {
			result.add(f.get());
		}
		return result;
	}

	public static <T> void forEach(List<T> list, Consumer<T> c){
		for(T i: list){
			c.accept(i);
		}
	}


	public static <T, R> List<R> map(List<T> list,
									 Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for(T s: list) {
			result.add(f.apply(s));
		}
		return result;
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


	public static String processIntString(int i,IntString ns){
		return ns.process(i);
	}

	public interface IntString{
		public String process(Integer i);
	}

	public static String processNullString(NullString ns){
		return ns.process();
	}

	public interface NullString{
		String process();
	}

	public static void processNullNull(NullNull nn){
		nn.process();

	}

	public interface NullNull{
		void process();
	}

	public static List<Apple> filter(List<Apple> inventory,
									 ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}



	interface ApplePredicate{
		boolean test(Apple a);
	}

	public static class Orange extends Fruit{
		private int weight = 0;
		public Orange(){

		}
		public Orange(int weight){
			this.weight=weight;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Orange{" +
					"weight=" + weight +
					'}';
		}
	}

	public static class Fruit{
		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Fruit{" +
					"name='" + name + '\'' +
					'}';
		}
	}
	public static class Apple extends Fruit{

		private int weight = 0;
		private String color = "";

		public Apple(){

		}

		public Apple(int weight, String color){
			this.weight = weight;
			this.color = color;
		}

		public Apple(Integer weight) {
			this.weight = weight;
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

}