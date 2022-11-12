package com.dmtryii.tasks.second;

/*
    Завдання #2:
    Написати метод, який на вхід приймає список рядків-текстів, в яких можуть бути хеш-теги (слова,
    що починаються на знак "#"). Як результат, метод повинен повертати top-5 найчастіше згаданих
    хеш-тегів із зазначенням кількості згадки кожного з них. Декілька однакових хеш-тегів в одному рядку
    повинні вважатися однією згадкою. Написати unit-тести для цього методу.
 */

import java.util.*;
import java.util.stream.Collectors;

public class ParserSecondTask {
    public Map<String, Integer> getTopTags(LinkedList<String> tags, int top) {
        return parseTags(tags)
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(top)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private HashMap<String, Integer> parseTags(LinkedList<String> tags) {
        HashMap<String, Integer> mapTags = new HashMap<>();
        for(String tag: tags) {
            Set<String> words = new HashSet<>(toWordsArray(tag));

            for(String word: words) {
                if(word.startsWith("#")) {
                    if(!mapTags.containsKey(word)) {
                        mapTags.put(word, 1);
                    } else {
                        incrementValue(mapTags, word);
                    }
                }
            }
        }
        return mapTags;
    }

    private void incrementValue(Map<String, Integer> map, String key) {
        Integer count = map.getOrDefault(key, map.get(key));
        map.put(key, count + 1);
    }

    private Set<String> toWordsArray(String s) {
        return new HashSet<>(List.of(s
                .toLowerCase()
                .split(" ")));
    }
}
