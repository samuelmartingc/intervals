package com.samuel.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntervalTest {
    private Interval interval_1_2;
    private Interval interval_2_1;
    private Interval interval_1_3;
    @Before
    public void init(){
        interval_1_2 = new Interval(1,2);
        interval_2_1 = new Interval(2,1);
        interval_1_3 = new Interval(1,3);
    }

    @Test
    public void intervalsEquality() {
        assertTrue(interval_1_2.equals(interval_1_2));
        assertTrue(interval_1_2.equals(interval_2_1));
        assertFalse(interval_1_2.equals(interval_1_3));
    }

}
