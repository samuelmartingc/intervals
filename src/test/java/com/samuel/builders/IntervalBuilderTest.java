package com.samuel.builders;

/**
 * Created by samuel on 20/11/16.
 */

import com.samuel.models.Interval;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntervalBuilderTest {
    private Interval interval_10_100 = new Interval(10,100);
    private Interval interval_20_30 = new Interval(20,30);
    private Interval interval_10_19 = new Interval(10,19);
    private Interval interval_31_100 = new Interval(31,100);

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
    public void build() {
        included.add(interval_10_100);
        excluded.add(interval_20_30);
        result.add(interval_10_19);
        result.add(interval_31_100);

        assertEquals(result,intervalBuilder.build(included,excluded));
    }

    @Test
    public void buildWithoutExcluded() {
        included.add(interval_10_100);
        result.add(interval_10_100);

        assertEquals(result,intervalBuilder.build(included,excluded));
    }


    @Test
    public void buildWithNullExcluded() {
        included.add(interval_10_100);
        excluded = null;
        result.add(interval_10_100);

        assertEquals(result,intervalBuilder.build(included,excluded));
    }

    @Test
    public void buildWithNullIncluded() {
        included = null;
        excluded.add(interval_10_100);

        assertEquals(result,intervalBuilder.build(included,excluded));
    }


    @Test
    public void buildWithoutIncluded() {
        included = new ArrayList<>();
        excluded.add(interval_10_100);

        assertEquals(result,intervalBuilder.build(included,excluded));
    }


    @Test
    public void buildWithDuplicatedIntervals() {
        included.add(interval_10_100);
        included.add(interval_10_100);
        excluded.add(interval_20_30);
        excluded.add(interval_20_30);
        excluded.add(interval_20_30);
        included.add(interval_10_100);
        included.add(interval_10_100);
        included.add(interval_10_100);
        excluded.add(interval_20_30);
        excluded.add(interval_20_30);

        result.add(interval_10_19);
        result.add(interval_31_100);

        assertEquals(result,intervalBuilder.build(included,excluded));
    }

    @Test
    public void buildWithoutAnyData() {
        included = null;
        excluded = null;

        assertEquals(result,intervalBuilder.build(included,excluded));
    }

}
