package com.dmtryii.tasks.third_task;

import com.dmtryii.tasks.third_task.shapes.Cylinder;
import com.dmtryii.tasks.third_task.shapes.Kube;
import com.dmtryii.tasks.third_task.shapes.Shape;
import com.dmtryii.tasks.third_task.shapes.Sphere;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ShapesThirdTaskTest {

    private ShapesThirdTask shape;
    private Shape kube;
    private Shape sphere;
    private Shape cylinder;
    Set<Shape> shapes;
    List<Shape> actual;
    List<Shape> expected;

    @Before
    public void setUp() {
        shape = new ShapesThirdTask();
    }

    @Test
    public void forEmptyList() {
        shapes = new HashSet<>();
        actual = shape.sortShapeByVolume(shapes);
        expected = new ArrayList<>();

        Assert.assertArrayEquals(new List[]{actual}, new List[]{expected});
    }

    @Test
    public void forOneListElement() {
        kube = new Kube(10.0);

        shapes = new HashSet<>();
        shapes.add(kube);

        actual = shape.sortShapeByVolume(shapes);
        expected = new ArrayList<>();
        expected.add(kube);

        Assert.assertArrayEquals(new List[]{actual}, new List[]{expected});
    }

    @Test
    public void sortShapeByVolume() {
        kube = new Kube(3.0);
        sphere = new Sphere(4.0);
        cylinder = new Cylinder(2.1, 4.0);

        shapes = new HashSet<>();
        shapes.add(kube);
        shapes.add(sphere);
        shapes.add(cylinder);

        actual = shape.sortShapeByVolume(shapes);
        expected = new ArrayList<>();
        expected.add(kube);
        expected.add(cylinder);
        expected.add(sphere);

        Assert.assertArrayEquals(new List[]{actual}, new List[]{expected});
    }
}