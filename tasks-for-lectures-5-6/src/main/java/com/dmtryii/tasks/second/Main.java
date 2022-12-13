package com.dmtryii.tasks.second;

import com.dmtryii.tasks.second.models.Model;
import com.dmtryii.tasks.second.utils.Creator;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Class<Model> c = Model.class;
        Path propertiesPath = Path.of(Creator.RESOURCES_PATH + Creator.FILE_NAME);
        Model model = Creator.loadFromProperties(c, propertiesPath);
        System.out.println(model);
    }
}
