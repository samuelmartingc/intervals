package com.samuel.models;

/**
 * Created by samuel on 19/11/16.
 */
public class Knot implements Comparable<Knot> {
    private final int value;
    private final PointType type;

    public Knot(int value, PointType type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public PointType getType() {
        return type;
    }

    @Override
    public int compareTo(Knot other) {
        if (other.value == this.value) {
            return this.type.ordinal() < other.type.ordinal() ? -1 : 1;
        } else {
            return this.value < other.value ? -1 : 1;
        }
    }

    @Override
    public String toString() {
        return "Knot{" +
                "value=" + value +
                ", type=" + type +
                '}';
    }

    public enum PointType {
        Last, LastGap, FirstGap, First
    }
}
