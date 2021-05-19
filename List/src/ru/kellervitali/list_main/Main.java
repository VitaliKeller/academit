package ru.kellervitali.list_main;

import ru.kellervitali.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertHead(1);
        list.insertHead(2);
        list.insertHead(3);
        list.insertHead(4);
        list.insertHead(5);

        System.out.println("list = " + list);
        System.out.println();

        // + 2.1 получение размера списка
        System.out.println("2.1. Получение размера списка. length = " + list.getSize());

        // + 2.2 получение значение первого элемента
        System.out.println("2.2. Получение значение первого элемента. head = " + list.getHead());

        // + 2.3 получение/изменение значения по указанному индексу. Изменение значения по индексу пусть выдает старое значение.
        System.out.println("2.3. Получение значения по указанному индексу. item 1 = " + list.getDataByIndex(1));
        // изменение:
        System.out.println("2.3. Изменение значения по указанному индексу. Пред.значение: " + list.setDataByIndex(10, 0) + ". Результат: " + list);
        System.out.println("2.3. Изменение значения по указанному индексу. Пред.значение: " + list.setDataByIndex(11, 4) + ". Результат: " + list);

        // + 2.4 удаление элемента по индексу, пусть выдает значение элемента
        System.out.println("2.4. Пред.значение: " + list.deleteItemByIndex(2) + ". Результат: " + list);

        // + 2.5 вставка элемента в начало
        list.insertHead(13);
        System.out.println("2.5. Вставка head. Результат: " + list);

        // + 2.6 вставка элемента по индексу
        list.addItemByIndex(2, 12);
        System.out.println("2.6. Вставка элемента по индексу 2. Результат: " + list);

        // + 2.7 удаление узла по значению, пусть выдает true, если элемент был удален
        System.out.println("2.7. Удаление узла по значению 2. Успешность: " + list.deleteItemByData(2) + ". Результат: " + list);

        // + 2.8 удаление первого элемента, пусть выдает значение элемента
        System.out.println("2.8. Удаление первого элемента. Пред.значение: " + list.deleteHead() + ". Результат: " + list);

        // 2.9 разворот списка за линейное время
        list.reverse();
        System.out.println("2.9. Разворот списка за линейное время.");
        System.out.println("list = " + list);
    }
}
