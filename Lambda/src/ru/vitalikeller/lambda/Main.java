package ru.vitalikeller.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Задача «Лямбды»
+ 1. Создать класс Person с полями имя и возраст.
Сделать конструктор, который принимает эти параметры.
Сделать геттеры для полей который принимает эти параметры.

+ 2. В main создать список из нескольких людей
3. При помощи лямбда функций:
 + А) получить список уникальных имен
 + Б) вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр.
 + В) получить список людей младше 18, посчитать для них средний возраст
 + Г) при помощи группировки получить Map , в котором ключи – имена, а значения – средний возраст
 + Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
 */

public class Main {
    public static void main(String[] args) {
        // задача 2.
        List<Person> personsList = Arrays.asList(
                new Person("Вася", 25),
                new Person("Жора", 56),
                new Person("Дима", 34),
                new Person("Дима", 10),
                new Person("Лена", 18),
                new Person("Лена", 27),
                new Person("Саша", 8)
        );

        personsList.forEach(System.out::println);

        // задача 3.А, 3.Б
        printDistinctNames(personsList);

        // задача 3.В
        printYoungerNamesAndAverageAge(personsList, 18);

        // задача 3.Г
        printNamesAndAverageAge(personsList);

        // задача 3.Д
        printNamesFromTo(personsList, 20, 45);
    }

    public static void printDistinctNames(List<Person> personsList) {
        List<String> distinctNames = personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println();
        System.out.println(distinctNames);

        String printNames = distinctNames
                .stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(printNames);
    }

    public static void printYoungerNamesAndAverageAge(List<Person> personsList, int age) {
        List<Person> youngerPersonList = personsList.stream()
                .filter(person -> person.getAge() < age)
                .collect(Collectors.toList());

        double youngerPersonsAverageAge = youngerPersonList.stream()
                .collect(Collectors.averagingDouble(Person::getAge));

        System.out.println();
        System.out.println(youngerPersonList);
        System.out.println("Средний возраст для person < " + age + " лет: " + youngerPersonsAverageAge);
    }

    public static void printNamesAndAverageAge(List<Person> personsList) {
        Map<String, Double> mapAvgAgeByName = personsList.stream().
                collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        // для интереса и проверки - подсчет количества
        Map<String, Long> mapCountAgeByName = personsList.stream().
                collect(Collectors.groupingBy(Person::getName, Collectors.counting()));

        System.out.println();
        System.out.println("Средний возраст по именам: " + mapAvgAgeByName);
        System.out.println("Количество по именам: " + mapCountAgeByName);
    }

    public static void printNamesFromTo(List<Person> personsList, int ageFrom, int ageTo) {
        List<Person> personNamesFromTo = personsList.stream()
                .filter(person -> person.getAge() >= ageFrom && person.getAge() <= ageTo)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("Персоны между " + ageFrom + " и " + ageTo + ": " + personNamesFromTo);

        personNamesFromTo.forEach(person -> System.out.println(person.getName()));
    }
}
