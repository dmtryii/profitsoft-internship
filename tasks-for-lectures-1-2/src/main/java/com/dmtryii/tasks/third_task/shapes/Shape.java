package com.dmtryii.tasks.third_task.shapes;

public abstract class Shape implements Comparable<Shape>{

    protected Double volume;

    protected abstract Double getVolume();

    public int compareTo(Shape shape) {
        return (int) (volume - shape.getVolume());
    }
}
