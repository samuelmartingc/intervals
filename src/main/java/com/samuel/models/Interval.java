package com.samuel.models;

/**
 * Created by samuel on 19/11/16.
 */
public class Interval {
    private final int first;
    private final int last;
    public Interval(int first, int last){
        if (first < last){
            this.first = first;
            this.last=last;
        } else {
            this.first = last;
            this.last = first;
        }
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "first=" + first +
                ", last=" + last +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (getFirst() != interval.getFirst()) return false;
        return getLast() == interval.getLast();

    }

    @Override
    public int hashCode() {
        int result = getFirst();
        result = 31 * result + getLast();
        return result;
    }
}
