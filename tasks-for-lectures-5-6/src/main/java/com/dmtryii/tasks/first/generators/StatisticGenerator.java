package com.dmtryii.tasks.first.generators;

import com.dmtryii.tasks.first.entity.ViolationType;
import com.dmtryii.tasks.first.parsers.CustomJsonParser;
import com.dmtryii.tasks.first.parsers.CustomXmlParser;
import com.dmtryii.tasks.first.writers.StatisticJsonWriter;
import com.dmtryii.tasks.first.writers.StatisticXmlWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticGenerator {
    private final CustomXmlParser xmlParser;
    private final CustomJsonParser jsonParser;
    private Map<ViolationType, Double> statistic;
    private String firstFileType = "";

    public StatisticGenerator() {
        this.statistic = new HashMap<>();
        this.xmlParser = new CustomXmlParser(statistic);
        this.jsonParser = new CustomJsonParser(statistic);
    }

    public void collectAndGenerateStatistic(String pathToFolder, String outputPath) {
        try (Stream<Path> paths = Files.walk(Paths.get(pathToFolder))) {
            paths
                    .filter(file -> !Files.isDirectory(file))
                    .forEach((file) -> {
                        if (file.toString().endsWith(".xml")) {
                            if (firstFileType.isEmpty()) firstFileType = "xml";
                            xmlParser.parse(file);
                        }
                        if (file.toString().endsWith(".json")) {
                            if (firstFileType.isEmpty()) firstFileType = "json";
                            jsonParser.parse(file);
                        }
                    });

            sortMapByFineAmount();
            generateStatistic(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void combineMapFromParsers() {
        mergeMap(xmlParser.getStatistic());
        mergeMap(jsonParser.getStatistic());
    }

    private void mergeMap(Map<ViolationType, Double> newStatistic) {
        newStatistic.forEach(
                ((violationType, fineAmout) -> statistic.merge(
                        violationType, fineAmout, (oldValue, newValue) -> oldValue += newValue
                ))
        );
    }

    private void generateStatistic(String outputPath) {
        switch (firstFileType) {
            case "xml":
                StatisticJsonWriter.create(statistic, outputPath);
                break;
            case "json":
                StatisticXmlWriter.create(statistic, outputPath);
                break;
        }
    }

    private void sortMapByFineAmount() {
        statistic = statistic.entrySet().stream()
                .sorted(Map.Entry.<ViolationType, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (i1, i2) -> i1, LinkedHashMap::new));
    }
}
