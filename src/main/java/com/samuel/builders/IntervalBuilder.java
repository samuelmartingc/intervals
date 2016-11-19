package com.samuel.builders;

import com.samuel.models.Interval;
import com.samuel.models.IntervalComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by samuel on 19/11/16.
 */
public class IntervalBuilder {
    public List<Interval> build (List<Interval> intervals){

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
}
