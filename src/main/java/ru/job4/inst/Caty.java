package ru.job4.inst;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Caty {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        String exit = "0";
        StringBuilder text = new StringBuilder();
        String str = "";
        while (!str.equals(exit)) {
            System.out.println("Введите строку. Для выхода введите " + exit + ".");
            str = in.nextLine();
            list.add(str);
            for (String i : list) {
                System.out.println(i);
            }
        }
        System.out.println("Результат: ");
        for (String i : list) {
            System.out.println(i);
        }
    }
}
