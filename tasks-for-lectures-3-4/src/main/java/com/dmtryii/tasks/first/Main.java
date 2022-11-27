package com.dmtryii.tasks.first;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        XMLParser parser = new XMLParser(
                new File("tasks-for-lectures-3-4/src/main/resources/first-task/input/test-file.xml"),
                "tasks-for-lectures-3-4/src/main/resources/first-task/output/");

        parser.parseAndWrite();

    }
}
