package com.dmtryii.tasks.first.parsers;

import com.dmtryii.tasks.first.entity.Violation;
import com.dmtryii.tasks.first.entity.ViolationType;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class CustomJsonParser {
    private final JsonFactory jsonFactory;
    private final Violation violation;
    private final Map<ViolationType, Double> statistic;

    public CustomJsonParser(Map<ViolationType, Double> statistic) {
        this.jsonFactory = new JsonFactory();
        this.violation = new Violation();
        this.statistic = statistic;
    }

    public void parse(Path path) {
        try (JsonParser jsonParser = jsonFactory.createParser(path.toFile())) {
            parse(jsonParser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parse(JsonParser parser) throws IOException {
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            parser.nextToken();
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = parser.getCurrentName();
                switch (fieldName) {
                    case "type": {
                        parser.nextToken();
                        violation.setType(ViolationType.valueOf(parser.getText()));
                        break;
                    }
                    case "fine_amount": {
                        parser.nextToken();
                        violation.setAmount(Double.parseDouble(parser.getText()));
                        break;
                    }
                }
            }
            statistic.computeIfPresent(violation.getType(), (key, value) -> value += violation.getAmount());
            statistic.putIfAbsent(violation.getType(), violation.getAmount());
        }
    }

    public Map<ViolationType, Double> getStatistic() {
        return statistic;
    }
}
