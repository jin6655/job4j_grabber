package ru.job4.gc;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftDemo {

    public static void main(String[] args) {
        //example1();
        example2();
    }

    private static void example1() {
        Object strong = new Object();
        SoftReference<Object> soft = new SoftReference<>(strong);
        strong = null;
        System.out.println("strong is " + strong);
        System.out.println("soft is " + soft.get());
    }

    private static void example2()  {
        List<SoftReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 50_000_000; i++) {
            int finalI = i;
            objects.add(new SoftReference<Object>(new Object() {
                String value = String.valueOf(System.currentTimeMillis());
                int n = finalI;

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed! " + n);
                }
            }));
        }
        System.gc();
        int liveObject = 0;
        for (SoftReference<Object> ref : objects) {
            Object object = ref.get();
            if (object != null) {
                liveObject++;
            }
        }
        System.out.println(liveObject);
    }

    }
