package ru.job4.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class MaxMinTest {

    private class Item implements Comparable<Item> {

        private int x;
        private String str;

        public Item(int x, String str) {
            this.x = x;
            this.str = str;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return x == item.x && Objects.equals(str, item.str);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, str);
        }

        @Override
        public int compareTo(Item o) {
            if (this.str.equals(o.str)) {
                return 0;
            }
            return this.str.compareTo(o.str);
        }

    }

    @Test
    public void WhenItemMaxString() {
        List<Item> list = List.of(
                new Item(1, "Anna"),
                new Item(2, "Oleg"),
                new Item(3, "Boris")
        );
        Item expected = new Item(2, "Oleg");
        Assert.assertEquals(expected, new MaxMin().max(list, Item::compareTo));
    }

   @Test
    public void WhenItemMinString() {
       List<Item> list = List.of(
               new Item(1, "Anna"),
               new Item(2, "Oleg"),
               new Item(3, "Boris")
       );
        Item expected = new Item(1, "Anna");
        Assert.assertEquals(expected, new MaxMin().min(list, Item::compareTo));
    }

    @Test
    public void WhenItemMaxInt() {
        List<Item> list = List.of(
                new Item(1, "Anna"),
                new Item(2, "Oleg"),
                new Item(3, "Boris")
        );
        Comparator<Item> comp = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.x == o2.x) {
                    return 0;
                }
                if (o1 == null) {
                    return -1;
                }
                if (o2 == null) {
                    return 1;
                }
                return o1.x > o2.x ? 1 : -1;
            }
        };
        Item expected = new Item(3, "Boris");
        Assert.assertEquals(expected, new MaxMin().max(list, comp));
    }

    @Test
    public void WhenItemMinInt() {
        List<Item> list = List.of(
                new Item(1, "Anna"),
                new Item(2, "Oleg"),
                new Item(3, "Boris")
        );
        Comparator<Item> comp = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.x == o2.x) {
                    return 0;
                }
                if (o1 == null) {
                    return -1;
                }
                if (o2 == null) {
                    return 1;
                }
                return o1.x > o2.x ? 1 : -1;
            }
        };
        Item expected = new Item(1, "Anna");
        Assert.assertEquals(expected.str, new MaxMin().min(list, comp).str);
    }

}