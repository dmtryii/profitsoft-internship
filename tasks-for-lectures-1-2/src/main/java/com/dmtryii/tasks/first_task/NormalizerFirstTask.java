package com.dmtryii.tasks.first_task;

/*
    Завдання #1:
    Зробити метод, який приймає на вхід масив цілих чисел, і повертає тільки ті з них,
    які позитивні (>=0), відсортувавши їх за спаданням. Зробити unit-тести для цього методу.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalizerFirstTask {
    public List<Integer> getSortedPositiveElements(ArrayList<Integer> array) {
        array.removeIf(num -> num < 0);
        array.sort(Collections.reverseOrder());
        return array;
    }
}
