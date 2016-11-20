package com.samuel.requirements;

import com.samuel.builders.IntervalBuilder;
import com.samuel.models.Interval;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequirementTest {
    public static final int AVG_MAX = 1000;
    private Interval interval_10_100 = new Interval(10,100);
    private Interval interval_20_30 = new Interval(20,30);
    private Interval interval_10_19 = new Interval(10,19);
    private Interval interval_31_100 = new Interval(31,100);
    private Interval interval_50_5000 = new Interval(50,5000);
    private Interval interval_10_5000 = new Interval(10,5000);
    private Interval interval_200_300 = new Interval(200,300);
    private Interval interval_400_500 = new Interval(400,500);
    private Interval interval_95_205 = new Interval(95,205);
    private Interval interval_410_420 = new Interval(410,420);
    private Interval interval_10_94 = new Interval(10,94);
    private Interval interval_206_300 = new Interval(206,300);
    private Interval interval_400_409 = new Interval(400,409);
    private Interval interval_421_500 = new Interval(421,500);

    private List<Interval> included;
    private List<Interval> excluded;
    private List<Interval> result;

    private IntervalBuilder intervalBuilder = new IntervalBuilder();

    @Before
    public void init(){

        included = new ArrayList<>();
        excluded = new ArrayList<>();
        result = new ArrayList<>();
    }

    @Test
    public void ex1() {
        included.add(interval_10_100);
        excluded.add(interval_20_30);
        result.add(interval_10_19);
        result.add(interval_31_100);

        assertEquals(result,intervalBuilder.build(included,excluded));
    }

    @Test
    public void ex2() {
        included.add(interval_50_5000);
        included.add(interval_10_100);
        result.add(interval_10_5000);

        assertEquals(result,intervalBuilder.build(included,excluded));
    }

    @Test
    public void ex3() {
        included.add(interval_10_100);
        included.add(interval_200_300);
        excluded.add(interval_95_205);
        result.add(interval_10_94);
        result.add(interval_206_300);

        assertEquals(result,intervalBuilder.build(included,excluded));
    }

    @Test
    public void ex4() {
        included.add(interval_10_100);
        included.add(interval_200_300);
        included.add(interval_400_500);
        excluded.add(interval_95_205);
        excluded.add(interval_410_420);
        result.add(interval_10_94);
        result.add(interval_206_300);
        result.add(interval_400_409);
        result.add(interval_421_500);

        assertEquals(result,intervalBuilder.build(included,excluded));
    }

    @Test
    @Ignore("too long")
    public void performance(){
        for (int numElems = 1000; numElems < 8000000; numElems = numElems*2){
            long time = 0;
            for (int countAverage = 0; countAverage< AVG_MAX; countAverage++){
                included = new ArrayList<>();
                excluded = new ArrayList<>();
                for (int i = 0; i< numElems; i=i+2){ //fill lists
                    if (i%4==0){
                        included.add(new Interval(i,i+1));
                    } else {
                        excluded.add(new Interval(i,i+1));
                    }
                }
                long start = System.nanoTime();
                intervalBuilder.build(included,excluded);
                long end = System.nanoTime();
                time += end-start;
            }
            System.out.println("average with numElems= " + numElems + ": -> " + (time/AVG_MAX)/1000 );
        }
    }
}
