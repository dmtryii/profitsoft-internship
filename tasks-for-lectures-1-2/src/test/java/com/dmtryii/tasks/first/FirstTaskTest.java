package com.dmtryii.tasks.first;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FirstTaskTest {

    private NormalizerFirstTask normalizer;

    @Before
    public void setUp() {
        normalizer = new NormalizerFirstTask();
    }

    @Test
    public void forEmptyArray() {
        ArrayList<Integer> actual = new ArrayList<>();
        normalizer.getSortedPositiveElements(actual);

        ArrayList<Integer> expected = new ArrayList<>();
        Assert.assertArrayEquals(new ArrayList[]{actual}, new ArrayList[]{expected});
    }

    @Test
    public void forOneArrayElement() {
        ArrayList<Integer> actual = new ArrayList<>();
        actual.add(0);
        normalizer.getSortedPositiveElements(actual);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);

        Assert.assertArrayEquals(new ArrayList[]{actual}, new ArrayList[]{expected});
    }

    @Test
    public void getSortedPositiveElements() {
        ArrayList<Integer> actual = new ArrayList<>();
        actual.add(-1);
        actual.add(1);
        actual.add(0);
        actual.add(-100);
        actual.add(50);
        normalizer.getSortedPositiveElements(actual);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(50);
        expected.add(1);
        expected.add(0);

        Assert.assertArrayEquals(new ArrayList[]{actual}, new ArrayList[]{expected});
    }
}