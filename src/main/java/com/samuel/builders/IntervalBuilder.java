package com.samuel.builders;

import com.samuel.models.Interval;
import com.samuel.models.IntervalComparator;
import com.samuel.models.Knot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by samuel on 19/11/16.
 */
public class IntervalBuilder {
    public List<Interval> preventOverlapping(List<Interval> intervals){

        if(intervals.size() == 0 || intervals.size() == 1)
            return intervals;
        Collections.sort(intervals, new IntervalComparator());

        Interval first = intervals.get(0);
        int start = first.getFirst();
        int end = first.getLast();
        ArrayList<Interval> result = new ArrayList<Interval>();
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (current.getFirst() <= end) {
                end = Math.max(current.getLast(), end);
            } else {
                result.add(new Interval(start, end));
                start = current.getFirst();
                end = current.getLast();
            }
        }
        result.add(new Interval(start, end));
        return result;
    }



    public List<Knot> createKnottedRope(List<Interval> included, List<Interval> excluded) {
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

    public List<Interval> resolve(List<Knot> knottedRope) {
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
