package ru.vitalikeller;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void printDistinctNames(List<Person> personList) {
        // todo собрать и распечатать уникальные
    }

    public static void main(String[] args) {
        List<Person> list = Arrays.asList(
                new Person("Вася", 25),
                new Person("Жора", 56),
                new Person("Дима", 34),
                new Person("Дима", 10),
                new Person("Лена", 18),
                new Person("Саша", 0));
    }
}
