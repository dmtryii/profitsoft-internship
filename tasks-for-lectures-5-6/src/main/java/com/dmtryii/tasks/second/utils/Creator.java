package com.dmtryii.tasks.second.utils;

import com.dmtryii.tasks.second.annotations.analyzers.PropertyAnalyzer;

import java.nio.file.Path;

public class Creator {
    public static final String RESOURCES_PATH = "src/main/resources/second-task/";
    public static final String FILE_NAME = "conf.properties";
    public static <T>T loadFromProperties(Class<T> c, Path propertiesPath) {
        PropertyAnalyzer analyzer = new PropertyAnalyzer();
        T model = createObjByClass(c);
        analyzer.analyzerProperty(c, model,propertiesPath);
        return model;
    }

    private static <T>T createObjByClass(Class<T> c) {
        try {
            return c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
