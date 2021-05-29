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

    public T getDataByIndex(int index) {
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
        ListItem<T> itemToDelete = getItemByIndex(index);

        T itemToDeleteData = itemToDelete.getData();

        if (index == 0) {
            head = head.getNext();
        } else {
            getItemByIndex(index - 1).setNext(itemToDelete.getNext());
        }

        size--;

        return itemToDeleteData;
    }

    // 2.5 вставка элемента в начало
    public void insertHead(T data) {
        head = new ListItem<>(data, head);

        size++;
    }

    // 2.6 вставка элемента по индексу
    public void addItemByIndex(int index, T data) {
        if (index == 0) {
            insertHead(data);
        } else {
            ListItem<T> p = getItemByIndex(index - 1);

            ListItem<T> newItem = new ListItem<>(data, p.getNext());

            p.setNext(newItem);

            size++;
        }
    }

    // 2.7 удаление узла по значению, пусть выдает true, если элемент был удален
    public boolean deleteItemByData(int data) {
        for (ListItem<T> p = head, prevItem = null; p != null; prevItem = p, p = p.getNext()) {
            if (Objects.equals(data, p.getData())) {
                if (prevItem != null) {
                    prevItem.setNext(p.getNext());

                    size--;
                } else {
                    head = p;
                }

                return true;
            }
        }

        return false;
    }

    // 2.8 удаление первого элемента, пусть выдает значение элемента
    public T deleteHead() {
        ListItem<T> nextItem = head.getNext();
        T headData = head.getData();

        head = nextItem;

        size--;

        return headData;
    }

    // 2.9 разворот списка за линейное время
    public void reverse() {
        for (ListItem<T> p = head, prevItem = null, prePrevItem = null; p != null; prePrevItem = prevItem, prevItem = p, p = p.getNext()) {
            if (prevItem != null) {
                prevItem.setNext(prePrevItem);
            }

            if (p.getNext() == null) {
                head = p;
                p.setNext(prevItem);

                return;
            }
        }
    }

    // 2.10 копирование списка
    public SinglyLinkedList<T> getCopy() {
        SinglyLinkedList<T> listClone = new SinglyLinkedList<>();

        if (size == 0 || head == null) {
            return listClone;
        }

        listClone.head = new ListItem<>(head.getData());
        listClone.size++;

        ListItem<T> previousCloneItem = listClone.head;

        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListItem<T> newListCloneItem = new ListItem<>(p.getData());


            previousCloneItem.setNext(newListCloneItem);

            listClone.size++; // можно одной командой всю длину прописать в начале метода, но решил что так точнее

            previousCloneItem = newListCloneItem;
        }

        return listClone;
    }

    @Override
    public String toString() {
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
