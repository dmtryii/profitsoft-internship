package com.dmtryii.tasks.third_task;

/*
    Завдання #3:
    Реалізувати метод, який сортує геометричні 3d фігури за об'ємом.
    Метод приймає параметром колекцію довільних геометричних фігур (куб, кулю, циліндр).
    Написати unit-тести для методу.
 */

import com.dmtryii.tasks.third_task.shapes.Shape;

import java.util.*;

public class ShapesThirdTask {

    public List<Shape> sortShapeByVolume(Set<Shape> shapes) {

        List<Shape> shapesList = new ArrayList<>(shapes);
        Collections.sort(shapesList);

        return shapesList;
    }
}
