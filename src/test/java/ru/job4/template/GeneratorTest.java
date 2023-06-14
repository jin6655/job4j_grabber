package ru.job4.template;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {


    /*@Test
    public void WhenProdeceString() {
        Generator generator = new GeneratorString();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> arg = Map.of("name", "Ivan Petrov", "subject", "you");
        String expected = "I am a Ivan Petrov, Who are you? ";
        assertEquals(expected, generator.produce(template, arg));
    }

    @Test(expected = IllegalArgumentException.class)
    public void WhenNumberOfArgumentsMapAndTemplateNotEqual() {
        Generator generator = new GeneratorString();
        String template = "I am a ${name}, ${year} , Who are ${subject}? ";
        Map<String, String> arg = Map.of("name", "Ivan Petrov", "subject", "you");
        generator.produce(template, arg);
    }

     */


}