package com.dmtryii.tasks.third;

/*
    Завдання #3:
    Реалізувати метод, який сортує геометричні 3d фігури за об'ємом.
    Метод приймає параметром колекцію довільних геометричних фігур (куб, кулю, циліндр).
    Написати unit-тести для методу.
 */

import com.dmtryii.tasks.third.shapes.Shape;
import lombok.NonNull;

import java.util.*;

public class ShapesThirdTask {

    public List<Shape> sortShapeByVolume(@NonNull Set<Shape> shapes) {

        List<Shape> shapesList = new ArrayList<>(shapes);
        Collections.sort(shapesList);

        return shapesList;
    }
}
