package com.samuel.models;

import java.util.Comparator;

/**
 * Created by samuel on 19/11/16.
 */
public class IntervalComparator implements Comparator<Interval> {
    public int compare(Interval i1, Interval i2) {
        return i1.getFirst() - i2.getFirst();
    }
}
