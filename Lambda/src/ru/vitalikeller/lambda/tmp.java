package ru.vitalikeller.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class tmp {
    public static void main(String[] args) {
        List<Person> g = Arrays.asList(
                new Person("Вася", 25),
                new Person("Жора", 56),
                new Person("Дима", 34),
                new Person("Дима", 10),
                new Person("Лена", 18),
                new Person("Саша", 8));

        g.forEach(System.out::println);

        Map<String, Double> mapAvgAgeByName = g.stream().
                collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        Map<String, Long> mapCountAgeByName = g.stream().
                collect(Collectors.groupingBy(Person::getName, Collectors.counting()));

        System.out.println(mapAvgAgeByName);
        System.out.println(mapCountAgeByName);
    }
}