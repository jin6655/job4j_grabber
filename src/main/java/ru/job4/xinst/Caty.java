package ru.job4.xinst;

import java.io.*;
import java.util.*;
public class Caty {

    private final Map<String, String> mapCat = new HashMap<>();

    static class Item implements Comparable<Item> {
        private int x;
        private String str;

        public Item(int x, String str) {
            this.x = x;
            this.str = str;
        }

        @Override
        public int compareTo(Item o1) {
            int rsl = 0;
            if (this.str.length() > o1.str.length()) {
                rsl = -1;
            } else {
                rsl = 1;
            }
            return rsl;
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Calendar date = Calendar.getInstance();
        date.set(2022, 7, 18);
        System.out.println(date.getTime());
    }

}