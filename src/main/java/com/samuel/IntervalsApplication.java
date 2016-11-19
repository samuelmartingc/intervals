package com.samuel;

import com.samuel.models.Interval;
import com.samuel.builders.IntervalBuilder;
import com.samuel.models.Knot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
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

        //flatten data
        included = intervalBuilder.preventOverlapping(included);

        System.out.println("Included");
        for(Interval i : included) {
            System.out.println(i);
        }

        excluded = intervalBuilder.preventOverlapping(excluded);

        System.out.println("Excluded");
        for(Interval i : excluded) {
            System.out.println(i);
        }


        //create string with knots(events)
        List<Knot> knottedRope = createKnottedRope(included, excluded);

        //process knots
        List<Interval> result = resolve(knottedRope);

        System.out.println("Result without preventing overlapping");
        for (Interval i : result) {
            System.out.println(i);
        }

        //flatten data
        System.out.println("Result with merge");
        result = intervalBuilder.preventOverlapping(result);
        for (Interval i : result) {
            System.out.println(i);
        }

    }

    private static List<Knot> createKnottedRope(List<Interval> included, List<Interval> excluded) {
        List<Knot> knottedRope = new ArrayList<>();
        for (Interval i : included) {
            knottedRope.add(new Knot(i.getFirst(), Knot.PointType.First));
            knottedRope.add(new Knot(i.getLast(), Knot.PointType.Last));
        }
        for (Interval i : excluded) {
            knottedRope.add(new Knot(i.getFirst(), Knot.PointType.FirstGap));
            knottedRope.add(new Knot(i.getLast(), Knot.PointType.LastGap));
        }

        // sort the rope
        Collections.sort(knottedRope);
        System.out.println("rope");
        for (Knot i : knottedRope) {
            System.out.println(i);
        }

        return knottedRope;
    }

    private static List<Interval> resolve(List<Knot> knottedRope) {
        List<Interval> result = new ArrayList<>();
        boolean isInterval = false;
        boolean isGap = false;
        int intervalStart = 0;
        for (Knot point : knottedRope) {
            switch (point.getType()) {
                case First:
                    if (!isGap) {
                        intervalStart = point.getValue();
                    }
                    isInterval = true;
                    break;
                case FirstGap:
                    if (isInterval) {
                        result.add(new Interval(intervalStart, point.getValue()));
                    }
                    isGap = true;
                    break;
                case Last:
                    if (!isGap) {
                        result.add(new Interval(intervalStart, point.getValue()));
                    }
                    isInterval = false;
                    break;
                case LastGap:
                    if (isInterval) {
                        intervalStart = point.getValue();
                    }
                    isGap = false;
                    break;
            }
        }
        return result;

	}

}
