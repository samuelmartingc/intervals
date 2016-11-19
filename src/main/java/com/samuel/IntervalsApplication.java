package com.samuel;

import com.samuel.models.Interval;
import com.samuel.builders.IntervalBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class IntervalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntervalsApplication.class, args);

        //input data
        Interval interval1 = new Interval(-1,10);
        Interval interval2 = new Interval(9,20);
        Interval interval3 = new Interval(19,30);
        Interval interval4 = new Interval(40,50);
        Interval interval5 = new Interval(45,60);
        Interval intervalA = new Interval(46,59);

        Interval interval6 = new Interval(5,10);
        Interval interval7 = new Interval(55,60);

        IntervalBuilder intervalBuilder = new IntervalBuilder();

        List<Interval> included = new ArrayList<>();
        included.add(interval1);
        included.add(interval2);
        included.add(interval3);
        included.add(interval4);
        included.add(interval5);
        included.add(intervalA);

        List<Interval> excluded = new ArrayList<>();
        excluded.add(interval6);
        excluded.add(interval7);

        List<Interval> result = intervalBuilder.build(included,excluded);

        System.out.println("Result");
        result = intervalBuilder.preventOverlapping(result);
        for (Interval i : result) {
            System.out.println(i);
        }
    }
}
