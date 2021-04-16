package ru.kellervitali;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        String path = "Numbers.txt";

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + path + " не найден!");
        }

        System.out.println("Прочитано: " + list);
    }

}
