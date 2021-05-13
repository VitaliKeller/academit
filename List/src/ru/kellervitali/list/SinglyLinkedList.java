package ru.kellervitali.list;
/*
        +1. Сделать классы для односвязного списка и узла списка.
         +  Для эффективности сделайте поле для хранения длины списка.

        2. Надо реализовать методы:
        + 2.1 получение размера списка
        + 2.2 получение значение первого элемента
        * 2.3 получение/изменение значения по указанному индексу. Изменение значения по индексу пусть выдает старое значение.
        2.4 удаление элемента по индексу, пусть выдает значение элемента
        + 2.5 вставка элемента в начало
        2.6 вставка элемента по индексу
        2.7 удаление узла по значению, пусть выдает true, если элемент был удален
        + 2.8 удаление первого элемента, пусть выдает значение элемента
        2.9 разворот списка за линейное время
        2.10 копирование списка
*/

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    // конструктор
    public SinglyLinkedList() {
    }

    // 2.1 получение размера списка
    public int getSize() {
        return length;
    }

    // 2.2 получение значение первого элемента
    public T getHead() {
        return head.getData();
    }

    // 2.3 получение/изменение значения по указанному индексу. Изменение значения по индексу пусть выдает старое значение.
    public T getItemByIndex(int index) {
        int i = 0;
        ListItem<T> p = head;

        while (i != index) {
            p = p.getNext();
            i++;
        }

        return p.getData();
    }

    public T setDataByIndex(T data, int index) {
        return null;
    }


    // 2.5 вставка элемента в начало
    public void insertHead(T data) {
        head = new ListItem<>(data, head);
        length++;
    }

    // 2.8 удаление первого элемента, пусть выдает значение элемента
    public ListItem<T> deleteHed() {
        ListItem<T> p0 = head.getNext();
        ListItem<T> p1 = head;

        head = p0;
        return p1;
    }


}
