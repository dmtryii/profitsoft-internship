package com.dmtryii.tasks.second.parsers;

import com.dmtryii.tasks.second.entity.Violation;
import com.dmtryii.tasks.second.entity.ViolationType;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class CustomXmlParser extends DefaultHandler {
    private final SAXParserFactory parserFactory;
    private String tagValue = null;

    private final Violation violation;
    private final Map<ViolationType, Double> statistic;

    public CustomXmlParser(Map<ViolationType, Double> statistic) {
        this.parserFactory = SAXParserFactory.newInstance();
        this.violation = new Violation();
        this.statistic = statistic;
    }

    public void parse(Path path) {
        try {
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(path.toFile(), this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "type":
                violation.setType(ViolationType.valueOf(tagValue));
                break;
            case "fine_amount":
                violation.setAmount(Double.parseDouble(tagValue));
                break;
            case "violation": {
                statistic.computeIfPresent(violation.getType(), (key, value) -> value += violation.getAmount());
                statistic.putIfAbsent(violation.getType(), violation.getAmount());
                break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        tagValue = String.copyValueOf(ch, start, length).trim();
    }
}
