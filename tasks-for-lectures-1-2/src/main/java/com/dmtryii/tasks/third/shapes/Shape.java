package com.dmtryii.tasks.third.shapes;

import lombok.ToString;

@ToString
public abstract class Shape implements Comparable<Shape>{

    protected Double volume;

    protected abstract Double getVolume();

    public int compareTo(Shape shape) {
        return (int) (volume - shape.getVolume());
    }
}
