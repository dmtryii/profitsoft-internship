package com.dmtryii.tasks.second;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ParserSecondTaskTest {

    private ParserSecondTask parser;
    private LinkedList<String> tags;
    private Map<String, Integer> mapTags;
    private Map<String, Integer> actual;
    private Map<String, Integer> expected;


    @Before
    public void setUp() {
        parser = new ParserSecondTask();
        tags = new LinkedList<>();
        mapTags = new HashMap<>();
        actual = new HashMap<>();
        expected = new HashMap<>();
    }

    @Test
    public void forEmptyList() {
        actual = parser.getTopTags(tags, 5);

        Assert.assertEquals(actual.keySet(), expected.keySet());
    }

    @Test
    public void getTopFiveTags() {

        tags.add("I heard there #was a secret chord " +
                "That David played #and it pleased the Lord " +
                "But you don't really care for music, do #you");
        tags.add("Well it goes like this the fourth, the fifth " +
                "The minor fall #and the major lift " +
                "The baffled king composing #Hallelujah");
        tags.add("#Hallelujah " +
                "#Hallelujah " +
                "#Hallelujah");
        tags.add("Well #your faith #was strong but you needed proof " +
                "#You saw her bathing on the roof " +
                "Her beauty #and the moonlight overthrew #you " +
                "#She tied #you to her kitchen chair");
        tags.add("#She broke #your throne #and #she cut #your hair " +
                "#And from #your lips, #she drew the #Hallelujah " +
                "#Hallelujah");

        actual = parser.getTopTags(tags, 5);

        expected.put("#and", 4);
        expected.put("#hallelujah", 3);
        expected.put("#your", 2);
        expected.put("#was", 2);
        expected.put("#she", 2);

        Assert.assertEquals(actual.keySet(), expected.keySet());
    }
}