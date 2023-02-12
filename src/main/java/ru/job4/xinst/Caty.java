package ru.job4.xinst;

public class Caty {

    private int num;
    private String name;

    public Caty(int num, String name) {
        this.num = num;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Caty{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Caty cat = new Caty(1, "sd");
        System.out.println(Integer.BYTES);
    }
}