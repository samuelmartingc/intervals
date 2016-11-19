package com.samuel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.samuel.models.Interval;
import com.samuel.builders.IntervalBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class IntervalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntervalsApplication.class, args);
        resolveIntervals(args);

    }

    private static void resolveIntervals(String[] args) {
        if (args.length == 0){
            System.out.println("Arguments are required -> \"[{\"first\":50,\"last\":100}]\"");
            return;
        }
        Type listIntervals = new TypeToken<ArrayList<Interval>>(){}.getType();
        Gson gson = new Gson();
        IntervalBuilder intervalBuilder = new IntervalBuilder();
            List<Interval> included = gson.fromJson(args[0] , listIntervals);
            List<Interval> excluded = (args.length == 1)?  new ArrayList<>(): gson.fromJson(args[1] , listIntervals);
            List<Interval> result = intervalBuilder.build(included,excluded);

            System.out.println("Result");
            result = intervalBuilder.preventOverlapping(result);
            for (Interval i : result) {
                System.out.println(i);
            }
    }
}
