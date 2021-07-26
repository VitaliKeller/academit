package ru.kellervitali.list;

import java.util.Objects;

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
        + 2.9 разворот списка за линейное время
        + 2.10 копирование списка
*/

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    // 2.1 получение размера списка
    public int getSize() {
        return size;
    }

    // 2.2 получение значение первого элемента
    public T getFirst() {
        if (head == null) {
            throw new NullPointerException("Отсутствует первый элемент списка");
        }

        return head.getData();
    }

    // 2.3 получение/изменение значения по указанному индексу. Изменение значения по индексу пусть выдает старое значение.
    private ListItem<T> getItemByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапазона");
        }

        int i = 0;
        ListItem<T> item = head;

        while (i != index) {
            item = item.getNext();
            i++;
        }

        return item;
    }

    public T getDataByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапазона");
        }

        return getItemByIndex(index).getData();
    }

    public T setDataByIndex(T data, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапазона");
        }

        ListItem<T> item = getItemByIndex(index);

        T itemPreviousData = item.getData();

        item.setData(data);

        return itemPreviousData;
    }

    // 2.4 удаление элемента по индексу, пусть выдает значение элемента
    public T deleteByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапазона");
        }

        T deletedItemData;

        if (index == 0) {
            deletedItemData = head.getData();

            head = head.getNext();
        } else {
            ListItem<T> previousItem = getItemByIndex(index - 1);

            deletedItemData = previousItem.getNext().getData();

            previousItem.setNext(previousItem.getNext().getNext());
        }

        size--;

        return deletedItemData;
    }

    // 2.5 вставка элемента в начало
    public void insertFirst(T data) {
        head = new ListItem<>(data, head);

        size++;
    }

    // 2.6 вставка элемента по индексу
    public void addByIndex(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс вне допустимого диапазона");
        }

        if (index == 0) {
            insertFirst(data);
        } else {
            ListItem<T> item = getItemByIndex(index - 1);

            ListItem<T> newItem = new ListItem<>(data, item.getNext());

            item.setNext(newItem);

            size++;
        }
    }

    // 2.7 удаление узла по значению, пусть выдает true, если элемент был удален
    public boolean deleteByData(int data) {
        for (ListItem<T> item = head, previousItem = null; item != null; previousItem = item, item = item.getNext()) {
            if (Objects.equals(data, item.getData())) {
                if (previousItem != null) {
                    previousItem.setNext(item.getNext());

                    size--;
                } else {
                    head = item.getNext();
                }

                return true;
            }
        }

        return false;
    }

    // 2.8 удаление первого элемента, пусть выдает значение элемента
    public T deleteFirst() {
        if (head == null) {
            throw new NullPointerException("Отсутствует первый элемент списка");
        }

        T headData = head.getData();

        head = head.getNext();
        ;

        size--;

        return headData;
    }

    // 2.9 разворот списка за линейное время
    public void reverse() {
        for (ListItem<T> item = head, previousItem = null, prePreviousItem = null; item != null; prePreviousItem = previousItem, previousItem = item, item = item.getNext()) {
            if (previousItem != null) {
                previousItem.setNext(prePreviousItem);
            }

            if (item.getNext() == null) {
                head = item;
                item.setNext(previousItem);

                return;
            }
        }
    }

    // 2.10 копирование списка
    public SinglyLinkedList<T> getCopy() {
        SinglyLinkedList<T> listClone = new SinglyLinkedList<>();

        if (size == 0) {
            return listClone;
        }

        listClone.head = new ListItem<>(head.getData());
        listClone.size = size;

        ListItem<T> previousCloneItem = listClone.head;

        for (ListItem<T> item = head.getNext(); item != null; item = item.getNext()) {
            ListItem<T> newListCloneItem = new ListItem<>(item.getData());

            previousCloneItem.setNext(newListCloneItem);

            previousCloneItem = newListCloneItem;
        }

        return listClone;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder list = new StringBuilder();
        list.append("[");

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            if (item.getNext() != null) {
                list.append(item.getData()).append(", ");
            } else {
                list.append(item.getData());
            }
        }

        list.append("]");

        return list.toString();
    }
}
