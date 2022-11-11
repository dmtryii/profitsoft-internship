package com.dmtryii.tasks.third_task.shapes;

public class Sphere extends Shape {

    private final Double radius;

    public Sphere(Double radius) {
        this.radius = radius;
        this.volume = getVolume();
    }

    @Override
    public Double getVolume() {
        return (4/3) * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "radius=" + radius +
                ", volume=" + volume +
                '}';
    }
}
