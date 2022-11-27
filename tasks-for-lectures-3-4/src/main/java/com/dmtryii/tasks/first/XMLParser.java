package com.dmtryii.tasks.first;

/*
    Завдання #1:
    Розробити програму, яка на вхід отримує xml-файл з тегами <person>, в яких є атрибути name і surname.
    Програма повинна створювати копію цього файлу, в якій значення атрибута surname об'єднане з name.
    Наприклад name="Тарас" surname="Шевченко" у вхідному файлі повинно бути замінене на name="Тарас Шевченко"
    (атрибут surname має бути видалений). Вхідний файл може бути великий, тому завантажувати його цілком в оперативну
    пам'ять буде поганою ідеєю. Опціонально (на макс. бал): зробити так,
    щоб форматування вихідного файла повторювало форматування вхідного
    файлу (мабуть, xml-парсер в такому разі тут не підійде).
 */

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParser {
    private final File file;
    private final String outputPath;
    private final Pattern namePattern =
            Pattern.compile("(?<!sur)name\\s?=\\s?\"(?<name>\\S+)\"", Pattern.MULTILINE | Pattern.COMMENTS);
    private final Pattern surnamePattern =
            Pattern.compile("surname\\s?=\\s?\"(?<surname>\\S+)\"", Pattern.MULTILINE | Pattern.COMMENTS);

    public XMLParser(File input, String outputPath) {
        this.file = input;
        this.outputPath = outputPath;
    }

    public void parseAndWrite() {
        StringBuilder resultLine;

        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                Scanner scanner = new Scanner(fileInputStream).useDelimiter("/>");
                FileWriter fileWriter = new FileWriter(outputPath + file.getName(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            while (scanner.hasNext()) {
                resultLine = new StringBuilder(toFullName(scanner.next()));

                if (scanner.hasNext()) {
                    bufferedWriter.write(resultLine + "/>");
                } else {
                    bufferedWriter.write(String.valueOf(resultLine));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toFullName(String line) {
        Matcher nameMatcher = namePattern.matcher(line);
        Matcher surnameMatcher = surnamePattern.matcher(line);

        String name = "";
        String surname = "";
        String resultLine = line;

        if (surnameMatcher.find()) {
            surname = surnameMatcher.group("surname");
            resultLine = resultLine.replaceAll(
                    surnamePattern.toString(),
                    ""
            );
        }

        if (nameMatcher.find()) {
            name = nameMatcher.group("name");
            resultLine = resultLine.replaceAll(
                    namePattern.toString(),
                    String.format("name=\"%s %s\"", name, surname)
            );
        }
        return resultLine;
    }
}
