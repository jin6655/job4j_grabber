package ru.job4.inst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Caty {

    private static final Logger LOG = LoggerFactory.getLogger(Caty.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("{} {}", name, age);
    }
}
