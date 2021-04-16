package ru.kellervitali;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

/*
Задача “ArrayListHome”
1. Прочитать в список все строки из файла
2. Есть список из целых чисел. Удалить из него все четные числа. В
    этой задаче новый список создавать нельзя
3. Есть список из целых чисел, в нём некоторые числа могут
    повторяться. Надо создать новый список, в котором будут
    элементы первого списка в таком же порядке, но без
    повторений
    Например, был список [1, 5, 2, 1, 3, 5], должен получиться новый
    список [1, 5, 2, 3]
*/

    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        String path = "Numbers.txt";

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                list1.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + path + " не найден!");
        }

        System.out.println("№1 Прочитано: " + list1);

        // задача 2
        // ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> list2 = getIntegersListFromStringsList(list1);

        System.out.println(System.lineSeparator() + "№2 Исходный: " + list2);
        removeEvenNumbers(list2);
        System.out.println("№2 Убраны четные: " + list2);

        // задача 3 - создаем новый список в статичном методе
        System.out.println(System.lineSeparator() + "№3 Убраны повторы: " + getDistinctNumbersList(list2));
    }

    private static void removeEvenNumbers(ArrayList<Integer> numbersList) {
        for (int i = 0; i < numbersList.size(); i++) {
            if (numbersList.get(i) % 2 == 0) {
                numbersList.remove(i);
                i--;
            }
        }
    }

    private static ArrayList<Integer> getDistinctNumbersList(ArrayList<Integer> numbersList) {
        ArrayList<Integer> resultList = new ArrayList<>();

        for (Integer e : numbersList) {
            if (!resultList.contains(e)) {
                resultList.add(e);
            }
        }

        return resultList;
    }

    private static ArrayList<Integer> getIntegersListFromStringsList(ArrayList<String> stringsList) {
        ArrayList<Integer> resultList = new ArrayList<>();

        for (String e : stringsList) {
            Integer tmp = getNumeric(e);

            if (tmp != null) {
                resultList.add(tmp);
            }
        }

        return resultList;
    }

    // проверка на число, исходник - https://java-lessons.ru/strings/check-if-string-a-number
    // тут нас интересуют только целые
    public static Integer getNumeric(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
