package com.dmtryii.tasks.third.shapes;

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
}
