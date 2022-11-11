package com.dmtryii.tasks.third_task.shapes;

import com.dmtryii.tasks.third_task.shapes.Shape;

public class Kube extends Shape {
    private final Double side;

    public Kube(Double side) {
        this.side = side;
        this.volume = getVolume();
    }

    @Override
    public Double getVolume() {
        return Math.pow(side, 3);
    }

    @Override
    public String toString() {
        return "Kube{" +
                "side=" + side +
                ", volume=" + volume +
                '}';
    }
}
