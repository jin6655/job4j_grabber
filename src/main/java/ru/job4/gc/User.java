package ru.job4.gc;

import java.util.Arrays;

public class User {

    private String age;
    private String name;
    private int number;
    private int[] massiv;
    private boolean active;

    public User(String age, String name, int number, int[] massiv, boolean active) {
        this.age = age;
        this.name = name;
        this.number = number;
        this.massiv = massiv;
        this.active = active;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed user %s%n", name);
    }

    /*
    Расчёт памяти, которую занимает пустой объект:
    12 байт на заголовок, итог - 16 байт после округления

    Расчёт памяти, которую занимает Объект User:  136 байт (129 байт до округления)
    - заголовок - 12 байт
    - входящие поля 40 + 40 + 4 + 32 + 1 = 117 байт
        - String age 3214 - 40 байт
            - заголовок 12 байт
            - ссылка на массив 4 байта
            - массив 24 байта
                - заголовок 16 байт (12 байт + 4 байта на длинну массива )
                - составляющие его 4 char 2 *4 = 8
        - String name Ann 40 байт
            - заголовок 12 байт
            - ссылка на массив 4 байта
            - массив 22 байта, 24 после округления
                - заголовок 16 байт (12 байт + 4 байта на длинну массива )
                - составляющие его 3 char 2 *3 = 6
        - int number 35 - 4 байта
        - int[] massiv {5, 8, 9} - 32 байта
            - заголовок  12 + 4, итог 16 байт
                - количество входящиех в него инт 4 * 3  - 16 байт
        - boolean active 1 байт

        Что происходит в программе  при её работе
         - Запускаем программ, которая в цикле N один за другуим создаёт объекты на которые не ведут ссылки и
           они должны очищаться при переполении памяти и сборки мусора
         - Мы получаем данные об удалённых объектах с помощью переопределённого метода finaliz
         - При перепролнеии области памяти Heap в области yong происходит заполнение ненужных объектов поочерёднов в зоны S0 и S1,
           выжившие объекты со временем перемещаются с зону Old generation
     */

    public static void main(String[] args) {
        User user01 = new User("3214", "Ann", 35, new int[]{5, 8, 9}, true);
        for (int i = 1; i < 10000; i++) {
            new User("N" + i, String.format("Object%d", i), i, new int[]{i + 1, i + 2}, i % 2 == 0);
        }
        user01 = new User("33", "Maik", 28, new int[]{5, 8}, false);
    }

}
