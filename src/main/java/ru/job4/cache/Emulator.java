package ru.job4.cache;

import ru.job4.xinst.Caty;

import java.io.UTFDataFormatException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Emulator {

    public static void showMenu() {
        String str = "";
        String strPath = "";
        System.out.println("Для начала работы укажите путь к файлам.");
        while (!str.equals("0")) {
            System.out.println("1. Выбрать путь к файлам.");
            System.out.println("0. Выход.");
            str = new Scanner(System.in).nextLine().toString();
            if (str.equals("1")) {
                System.out.println("Укажите путь к файлам.");
                strPath = new Scanner(System.in).nextLine().toString();
                while (!Files.exists(Paths.get(strPath))) {
                    System.out.println("Указанной директории не существует. Для начала работы введите путь файлами для чтения. Для выхода нажмите 0.");
                    strPath = new Scanner(System.in).nextLine().toString();
                    if (strPath.equals("0")) {
                        str.equals("0");
                        break;
                    }
                }
                if (!strPath.equals("0")) {
                    System.out.println("Директория " + strPath + " добавлена.");
                    break;
                } else {
                    break;
                }
            }
        }
        DirFileCache dirC = new DirFileCache(strPath);
        while (!str.equals("0")) {
            System.out.println("2. Выбрать файл.");
            System.out.println("0. Выход.");
            str = new Scanner(System.in).nextLine().toString();
            if (str.equals("2")) {
                System.out.println("Введите название файла  в формате txt.");
                str = new Scanner(System.in).nextLine().toString();
                Path file = Paths.get(new StringBuilder().append(dirC.getCachingDir()).append("\\").append(str).append(".txt").toString());
                System.out.println(dirC.getCachingDir());
                System.out.println(file.toString());
                while (!Files.exists(file)) {
                    System.out.println("Указанный файл/директория не существуют или его формат не соответствует .txt. Введите имя файла/директории с расширением .txt. Для выхода нажмите 0.");
                    str = new Scanner(System.in).nextLine().toString();
                    file = Paths.get(new StringBuilder().append(dirC.getCachingDir()).append("\\").append(str).append(".txt").toString());
                    if (str.equals("0")) {
                        break;
                    }
                }
                System.out.println("Результат запроса: ");
                String rsl = dirC.get(str);
                System.out.println(rsl);
            }
        }
    }

    public static void main(String[] args) {
        showMenu();
    }
}
