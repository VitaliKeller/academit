package ru.vitalikeller.hashtable_main;

/*
Задача HashTable
        Лекции, нужные для решения задачи: 1-8, 10 понятие итератора, 13.
        Сделать свою реализацию хэш-таблицы, сделать ее generic’ом.
        Класс должен реализовать интерфейс Collection<T> (ICollection<T> в C#).
        И в одном из конструкторов сделать параметр, который задает размер массива хэш-таблицы.

Чему научитесь:
•	Понимание работы хэш-таблицы
•	Реализация интересного итератора
•	Generic’и
*/

import ru.vitalikeller.hashtable.HashTable;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<Integer> ht1 = new HashTable<>(5);
        System.out.println(ht1);
        System.out.println(ht1.size());
        System.out.println(ht1.isEmpty());

        ht1.add(1);
        ht1.add(2);
        ht1.add(3);
        ht1.add(4);
        //System.out.println(ht1);
        ht1.add(5);
        ht1.add(5);
        ht1.add(15);
        ht1.add(6);
        ht1.add(8);
        ht1.add(null);
        //System.out.println(ht1);
        ht1.add(100);
        ht1.add(101);
        ht1.add(102);
        ht1.add(103);
        System.out.println(ht1);

        ht1.remove(100);
        System.out.println(ht1);

        System.out.println(ht1.size());
        System.out.println(ht1.isEmpty());

        ht1.clear();
        System.out.println(ht1);
        System.out.println(ht1.size());
        System.out.println(ht1.isEmpty());

        // -------------
        System.out.println();

        Collection<Integer> ht2 = new HashTable<>();
        ht2.add(1);
        ht2.add(2);
        ht2.add(3);
        System.out.println(ht2);
        System.out.println(ht2.toArray());

        // -------------
        System.out.println();

        //System.out.println(ht1.containsAll(ht2));
        // todo ошибка... если во второй коллекции null. Нужен иторатор

        ht2.add(200);
        System.out.println(ht2);
        //System.out.println(ht1.containsAll(ht2));
        //System.out.println(ht1.removeAll(ht2));
    }
}
