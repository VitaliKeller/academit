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

public class Main {
    public static void main(String[] args) {
        myArrayList<Integer> myList1 = new myArrayList<>();
        myList1.add(1);
        myList1.add(4);
        System.out.println("myList1 пуст?: " + myList1.isEmpty());

        myArrayList<Integer> myList = new myArrayList<>(1);
        System.out.println("myList пуст?: " + myList.isEmpty());

        myList.add(null);
        myList.add(-1);
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);

        System.out.println(myList);
        System.out.println("myList пуст: " + myList.isEmpty());

        myList.trimToSize();


        Integer num = 3;
        System.out.println("Содержит элемент 3" + myList.contains(num));
        System.out.println("Удалить 3: " + myList.remove(num));
        System.out.println(myList);

        System.out.println("Удалить индекс 3: " + myList.remove(3));
        System.out.println(myList);

        System.out.println("Добавить в myList первую коллекцию myList1 (на индекс length): " + myList.addAll(myList.size(), myList1));
        System.out.println(myList);

        System.out.println("Добавить в myList первую коллекцию myList1 (просто): " + myList.addAll(myList1));
        System.out.println(myList);

        System.out.println("Содержатся ли все элементы myList в myList1: " + myList1.containsAll(myList));
        System.out.println("Содержатся ли все элементы myList1 в myList: " + myList.containsAll(myList1));

        myList.retainAll(myList1);
        System.out.println("Список myList, после оставления в нем элементов из myList1: " + myList);

        myList.removeAll(myList1);
        System.out.println("Список myList, после исключения элементов из myList1: " + myList);
    }
}
