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
        // Задача 1
        String path = "Numbers.txt";
        ArrayList<String> stringsListFromFile = getListFromFile(path);

        // задача 2
        // ArrayList<Integer> integersList = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> integersList = getIntegersListFromStringsList(stringsListFromFile);

        System.out.println(System.lineSeparator() + "№2 Исходный: " + integersList);
        removeEvenNumbers(integersList);
        System.out.println("№2 Убраны четные: " + integersList);

        // задача 3 - создаем новый список в статичном методе
        System.out.println(System.lineSeparator() + "№3 Убраны повторы: " + getDistinctNumbersList(integersList));
    }

    public static ArrayList<String> getListFromFile(String path) {
        ArrayList<String> listFromFile = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                listFromFile.add(scanner.nextLine());
            }

            System.out.println("№1 Прочитано: " + listFromFile);
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + path + " не найден!");
        }

        return listFromFile;
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
        ArrayList<Integer> distinctNumbersList = new ArrayList<>(20);

        for (Integer e : numbersList) {
            if (!distinctNumbersList.contains(e)) {
                distinctNumbersList.add(e);
            }
        }

        distinctNumbersList.trimToSize();
        return distinctNumbersList;
    }

    private static ArrayList<Integer> getIntegersListFromStringsList(ArrayList<String> stringsList) {
        ArrayList<Integer> resultList = new ArrayList<>(20);

        for (String e : stringsList) {
            try {
                resultList.add(Integer.parseInt(e));
            } catch (NumberFormatException ignored) {
            }
        }

        resultList.trimToSize();
        return resultList;
    }

    // проверка на число, исходник - https://java-lessons.ru/strings/check-if-string-a-number
    // тут нас интересуют только целые
}
