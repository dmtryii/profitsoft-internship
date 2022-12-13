package com.dmtryii.tasks.second.annotations.analyzers;

import com.dmtryii.tasks.second.annotations.Property;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class PropertyAnalyzer {
    public void analyzerProperty(Class<?> c, Object workingObject, Path propertiesPath){
        Properties properties = new Properties();

        try(InputStream input = new FileInputStream(propertiesPath.toFile())) {
            properties.load(input);

            for(Field field: c.getDeclaredFields()) {
                field.setAccessible(true);
                handleField(workingObject, field, properties);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleField(Object object, Field field, Properties prop) {
        String key;
        if(field.isAnnotationPresent(Property.class)) {
            Property annotation = field.getAnnotation(Property.class);

            if(annotation.name().length() > 0) {
                key = annotation.name();
            } else {
                key = field.getName();
            }

        } else {
            key = field.getName();
        }

        String property = prop.getProperty(key);

        try {
            if(key.startsWith("string")) {
                field.set(object, property);
            } else if(key.startsWith("number")) {
                field.setInt(object, Integer.parseInt(property));
            } else if(key.startsWith("time")) {

                String format = field.getAnnotation(Property.class).format();
                if(format.length() < 1) {
                    throw new RuntimeException("Property has not correct format for date");
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                LocalDateTime date = LocalDateTime.parse(property, formatter);
                field.set(object, date);
            }
        } catch (IllegalAccessException exp) {
            throw new RuntimeException(exp);
        }
    }
}
