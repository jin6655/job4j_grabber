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
        List<Item>  list = new ArrayList<>();
        list.add(new Item(1, "aaaaa"));
        list.add(new Item(4, "bbb"));
        list.add(new Item(8, "oooo"));
        Comparator<Item> comp = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                int rsl = 0;
                if (o1.x > o2.x) {
                    rsl = -1;
                } else {
                    rsl = 1;
                }
                return rsl;
            }
        };
        //Collections.sort(list, comp);
        Collections.sort(list);
        list.stream().map(s -> s.str).forEach(System.out::println);
    }

}