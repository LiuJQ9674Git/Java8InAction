package lambdasinaction.chap3;

import java.util.function.DoubleFunction;
import java.util.function.Function;

public class Letter{

    public static void main(String[] args){
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
                = addHeader.andThen(Letter::checkSpelling) .andThen(Letter::addFooter);
        String result=transformationPipeline.apply("labda");
        //From Raoul, Mario and Alan: test Kind regards
        //From Raoul, Mario and Alan: lambda Kind regards
        System.out.println("result:\t"+ result);

        Function<String, String> transformationPipelineOnly
                = addHeader.andThen(Letter::addFooter);
        result=transformationPipelineOnly.apply("labda");
        System.out.println("result:\t"+ result);

        //
        double d=integrate((double x) -> x + 10, 3, 7);
        System.out.println("d:\t"+ d);

    }

    public static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b-a) / 2.0;
    }

    public static String addHeader(String text){
        return "From Raoul, Mario and Alan: " + text;
    }
    public static String addFooter(String text){
        return text + " Kind regards";
    }
    public static String checkSpelling(String text){
        return text.replaceAll("labda", "lambda");
    }
}
