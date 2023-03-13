package ru.job4.gc;

import java.util.Random;

public class GCTypeDemo {

    // VM options     -XX:+UseParallelGC -Xlog:gc*

    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        String[] data = new String[1_000_000];
        for (int i = 0; ; i = (i + 1) % data.length) {
            data[i] = String.valueOf((char) random.nextInt(255)).repeat(10);
        }
    }

}
