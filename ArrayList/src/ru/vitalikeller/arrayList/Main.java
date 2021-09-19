package ru.vitalikeller.arrayList;

/*
Задача. ArrayList
Лекции, нужные для решения задачи: 1-8, 10 понятие итератора, 13.

Сделать свою реализацию списка ArrayList<T>, сделать его generic’ом.
1. Необходимо реализовать интерфейс List<T>.
2. Нужно реализовать специфичный конструктор, принимающий вместимость,
3. а также методы ensureCapacity и trimToSize.

* В Java методы sublist и listIterator реализовывать не нужно.

Чему научитесь:
•	Понимание работы списка на массиве
•	Реализация сложного интерфейса
•	Реализация итератора
•	Больше будете думать о временных оценках
•	Generic’и
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        myArrayList<Integer> myArrayList = new myArrayList<>(1);

        System.out.println("Is myArrayList empty: " + myArrayList.isEmpty());

        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);

    }
}
