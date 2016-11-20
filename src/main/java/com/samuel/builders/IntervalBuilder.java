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

    public List<Interval> build (List<Interval> included, List<Interval> excluded) {
        List<Knot> knots = createKnottedRope(preventOverlapping(included), preventOverlapping(excluded));
        return preventOverlapping(resolve(knots));
    }

    private List<Interval> preventOverlapping(List<Interval> intervals){
        if (intervals == null){
            return new ArrayList<>();
        }
        if(intervals.isEmpty() || intervals.size() == 1)
            return intervals;
        Collections.sort(intervals, new IntervalComparator());
        List<Interval> result = preventOverlappingLoop(intervals);
        return result;
    }

    private List<Interval> preventOverlappingLoop(List<Interval> intervals) {
        Interval first = intervals.get(0);
        int start = first.getFirst();
        int end = first.getLast();
        List<Interval> result = new ArrayList<>();
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

    private List<Knot> createKnottedRope(List<Interval> included, List<Interval> excluded) {
        List<Knot> knottedRope = new ArrayList<>();
        for (Interval i : included) {
            knottedRope.add(new Knot(i.getFirst(), Knot.PointType.First));
            knottedRope.add(new Knot(i.getLast(), Knot.PointType.Last));
        }
        for (Interval i : excluded) {
            knottedRope.add(new Knot(i.getFirst(), Knot.PointType.FirstGap));
            knottedRope.add(new Knot(i.getLast(), Knot.PointType.LastGap));
        }
        Collections.sort(knottedRope);
        return knottedRope;
    }

    private List<Interval> resolve(List<Knot> knottedRope) {
        List<Interval> result = new ArrayList<>();
        boolean isInterval = false;
        boolean isGap = false;
        boolean isFirstStartPoint = true; // without this, if there is two consecutive starts, the second overrides the first
        int intervalStart = 0;
        for (Knot point : knottedRope) {
            switch (point.getType()) {
                case First:
                    if (!isGap) {
                        if (isFirstStartPoint){
                            intervalStart = point.getValue();
                        }
                        isFirstStartPoint = false;
                    }
                    isInterval = true;
                    break;
                case FirstGap:
                    if (isInterval) {
                        result.add(new Interval(intervalStart, point.getValue() -1));
                        isFirstStartPoint = true;
                    }
                    isGap = true;
                    break;
                case Last:
                    if (!isGap) {
                        result.add(new Interval(intervalStart, point.getValue()));
                        isFirstStartPoint = true;
                    }
                    isInterval = false;
                    break;
                case LastGap:
                    if (isInterval) {
                        if (isFirstStartPoint) {
                            intervalStart = point.getValue() + 1;
                        }
                        isFirstStartPoint = false;
                    }
                    isGap = false;
                    break;
            }
        }
        return result;
    }

}
