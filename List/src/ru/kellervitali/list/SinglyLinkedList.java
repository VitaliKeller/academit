package ru.kellervitali.list;
/*
        +1. Сделать классы для односвязного списка и узла списка.
         +  Для эффективности сделайте поле для хранения длины списка.

        2. Надо реализовать методы:
        + 2.1 получение размера списка
        + 2.2 получение значение первого элемента
        + 2.3 получение/изменение значения по указанному индексу. Изменение значения по индексу пусть выдает старое значение.
        + 2.4 удаление элемента по индексу, пусть выдает значение элемента
        + 2.5 вставка элемента в начало
        + 2.6 вставка элемента по индексу
        + 2.7 удаление узла по значению, пусть выдает true, если элемент был удален
        + 2.8 удаление первого элемента, пусть выдает значение элемента
        2.9 разворот списка за линейное время
        2.10 копирование списка
*/

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    // конструктор
    public SinglyLinkedList() {
    }

    //todo нет проверок на вылет за границы и отсутствие элементов ((

    // 2.1 получение размера списка
    public int getSize() {
        return length;
    }

    // 2.2 получение значение первого элемента
    public T getHead() {
        return head.getData();
    }

    // 2.3 получение/изменение значения по указанному индексу. Изменение значения по индексу пусть выдает старое значение.
    public ListItem<T> getItemByIndex(int index) {
        int i = 0;
        ListItem<T> p = head;

        while (i != index) {
            p = p.getNext();
            i++;
        }

        return p;
    }

    public T getDataByIndex(T data, int index) {
        return getItemByIndex(index).getData();
    }

    public T setDataByIndex(T data, int index) {
        ListItem<T> p = getItemByIndex(index);

        T pData = p.getData();
        p.setData(data);

        return pData;
    }

    // 2.4 удаление элемента по индексу, пусть выдает значение элемента

    public T deleteItemByIndex(int index) {
        if (index == 0) {
            System.out.println("ошибка! нет первого элемента");
        }

        ListItem<T> prevItem = getItemByIndex(index - 1);
        ListItem<T> currentItem = getItemByIndex(index);

        T deletedItemData = currentItem.getData();

        prevItem.setNext(currentItem.getNext());
        length--;

        return deletedItemData;
    }

    // 2.5 вставка элемента в начало
    public void insertHead(T data) {
        head = new ListItem<>(data, head);
        length++;
    }

    // 2.6 вставка элемента по индексу
    public void addItemByIndex(int index, T data) {
        if (index == 0) {
            insertHead(data);
        } else {
            ListItem<T> p = getItemByIndex(index - 1);
            ListItem<T> newItem = new ListItem<>(data, p.getNext());

            p.setNext(newItem);
            length++;
        }
    }

    // 2.7 удаление узла по значению, пусть выдает true, если элемент был удален
    public boolean deleteItemByData(int data) {
        for (ListItem<T> p = head, prevItem = null; p != null; prevItem = p, p = p.getNext()) {
            if (Objects.equals(data, p.getData())) {
                if (prevItem != null) {
                    prevItem.setNext(p.getNext());

                    length--;
                } else {
                    head = p;
                }

                return true;
            }
        }

        return false;
    }

    // 2.8 удаление первого элемента, пусть выдает значение элемента
    public ListItem<T> deleteHead() {
        ListItem<T> p0 = head.getNext();
        ListItem<T> p1 = head;

        head = p0;
        return p1;
    }
}
