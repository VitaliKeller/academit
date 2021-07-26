package ru.kellervitali.list_main;

import ru.kellervitali.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertFirst(4);
        list.insertFirst(5);

        System.out.println("list = " + list);
        System.out.println();

        // + 2.1 получение размера списка
        System.out.println("2.1. Получение размера списка. length = " + list.getSize());

        // + 2.2 получение значение первого элемента
        System.out.println("2.2. Получение значение первого элемента. head = " + list.getFirst());

        // + 2.3 получение/изменение значения по указанному индексу. Изменение значения по индексу пусть выдает старое значение.
        System.out.println("2.3. Получение значения по указанному индексу 1. item 1 = " + list.getDataByIndex(1));
        // изменение:
        System.out.println("2.3. Изменение значения на 10, по указанному индексу 0. Пред.значение: " + list.setDataByIndex(10, 0) + ". Результат: " + list);
        System.out.println("2.3. Изменение значения на 11, по указанному индексу 4. Пред.значение: " + list.setDataByIndex(11, 4) + ". Результат: " + list);

        // + 2.4 удаление элемента по индексу, пусть выдает значение элемента
        System.out.println("2.4. Удаление элемента по индексу 2. Пред.значение: " + list.deleteByIndex(2) + ". Результат: " + list);

        // + 2.5 вставка элемента в начало
        list.insertFirst(13);
        System.out.println("2.5. Вставка head = 13. Результат: " + list + ". length = " + list.getSize());

        // + 2.6 вставка элемента по индексу
        list.addByIndex(2, 12);
        System.out.println("2.6. Вставка элемента 12 по индексу 2. Результат: " + list);

        // + 2.7 удаление узла по значению, пусть выдает true, если элемент был удален
        System.out.println("2.7. Удаление узла по значению 13. Успешность: " + list.deleteByData(13) + ". Результат: " + list);

        // + 2.8 удаление первого элемента, пусть выдает значение элемента
        System.out.println("2.8. Удаление первого элемента. Пред.значение: " + list.deleteFirst() + ". Результат: " + list);

        // 2.9 разворот списка за линейное время
        list.reverse();
        System.out.println("2.9. Разворот списка за линейное время. Результат: " + list);

        // 2.10 копирование списка
        System.out.println("2.10. Копирование списка. Результат: " + list.getCopy());
    }
}
