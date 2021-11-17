package ru.vitalikeller.array_list_main;

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

import ru.vitalikeller.array_list.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(1);
        list1.add(4);
        System.out.println("list1 пуст?: " + list1.isEmpty());

        MyArrayList<Integer> list2 = new MyArrayList<>(1);
        System.out.println("list2 пуст?: " + list2.isEmpty());

        list2.add(null);
        list2.add(-1);
        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);

        System.out.println(list2);
        System.out.println("list2 пуст: " + list2.isEmpty());

        list2.trimToSize();


        Integer num = 3;
        System.out.println("Содержит элемент 3: " + list2.contains(num));
        System.out.println("Удалить 3: " + list2.remove(num));
        System.out.println(list2);

        System.out.println("Удалить индекс 3: " + list2.remove(3));
        System.out.println(list2);

        System.out.println("Добавить в list2 первую коллекцию list1 (на индекс size): " + list2.addAll(list2.size(), list1));
        System.out.println(list2);

        System.out.println("Добавить в list2 первую коллекцию list1 (просто): " + list2.addAll(list1));
        System.out.println(list2);

        System.out.println("Содержатся ли все элементы list2 в list1: " + list1.containsAll(list2));
        System.out.println("Содержатся ли все элементы list1 в list2: " + list2.containsAll(list1));

        list2.retainAll(list1);
        System.out.println("Список list2, после оставления в нем элементов из list1: " + list2);

        list2.removeAll(list1);
        System.out.println("Список list2, после исключения элементов из list1: " + list2);
    }
}
