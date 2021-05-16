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

        System.out.println("length = " + list.getSize());
        System.out.println();

        System.out.println("head = " + list.getHead());
        System.out.println();

        System.out.println("item 0 = " + list.getDataByIndex(0) + ", head =" + list.getHead().toString() + "");
        System.out.println("item 1 = " + list.getDataByIndex(1));
        System.out.println();
    }
}
