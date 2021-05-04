package ru.vitalikeller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/*
Задача «Лямбды»
1. Создать класс Person с полями имя и возраст.
Сделать конструктор, который принимает эти параметры.
Сделать геттеры для полей который принимает эти параметры.

2. В main создать список из нескольких людей
3. При помощи лямбда функций:
 А) получить список уникальных имен
 Б) вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр.
 В) получить список людей младше 18, посчитать для них средний возраст
 Г) при помощи группировки получить Map , в котором ключи – имена, а значения – средний возраст
 Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста*/

public class Main {
    public static void main(String[] args) {
        // задача 2.
        List<Person> personsList = Arrays.asList(
                new Person("Вася", 25),
                new Person("Жора", 56),
                new Person("Дима", 34),
                new Person("Дима", 10),
                new Person("Лена", 18),
                new Person("Саша", -1));

        personsList.forEach(System.out::println);

        // задача 3.а, 3.б
        printDistinctNames(personsList);
    }

    public static void printDistinctNames(List<Person> personsList) {
        List<String> distinctNames = personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        String printNames = distinctNames
                .stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println();

        System.out.println(printNames);
    }
}
