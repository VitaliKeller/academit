package ru.vitalikeller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void printDistinctNames(List<Person> personList) {
        // todo собрать и распечатать уникальные
    }

    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(
                new Person("Вася", 25),
                new Person("Жора", 56),
                new Person("Дима", 34),
                new Person("Дима", 10),
                new Person("Лена", 18),
                new Person("Саша", -1));

        personList.forEach(System.out::println);
        System.out.println(" конец коллекции ");


        List<Person> persons = personList;
        List<String> filteredByNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println();

        String printNames = filteredByNames
                .stream()
                .collect(Collectors.joining(", ", "Names: ", "."));

        System.out.println(printNames);
        System.out.println("__________________");

    }
}
