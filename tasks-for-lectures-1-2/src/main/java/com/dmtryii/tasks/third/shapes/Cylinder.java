package com.dmtryii.tasks.third.shapes;

public class Cylinder extends Shape {

    private final Double height;
    private final Double radius;

    public Cylinder(Double height, Double radius) {
        this.height = height;
        this.radius = radius;
        this.volume = getVolume();
    }

    @Override
    public Double getVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", radius=" + radius +
                ", volume=" + volume +
                '}';
    }
}
