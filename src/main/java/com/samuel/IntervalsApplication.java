package com.samuel;

import com.samuel.models.Interval;
import com.samuel.models.IntervalComparator;
import com.samuel.builders.IntervalBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class IntervalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntervalsApplication.class, args);
        Interval interval1 = new Interval(1,10);
        Interval interval2 = new Interval(9,20);
        Interval interval3 = new Interval(19,30);
        Interval interval4 = new Interval(40,50);
        Interval interval5 = new Interval(45,60);

        IntervalBuilder intervalBuilder = new IntervalBuilder();

        List<Interval> included = new ArrayList<>();
        included.add(interval1);
        included.add(interval2);
        included.add(interval3);
        included.add(interval4);
        included.add(interval5);

        included = intervalBuilder.build(included);

        for(Interval i : included)
        {
            System.out.println(i);
        }

	}

}
