package ru.kellervitali.shape_main;

import ru.kellervitali.shape_classes.*;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{
                new Circle(2.5),
                new Circle(4.1),
                new Triangle(1, 1, 2, 2, 3, 3),
                new Triangle(3, 1, 2, 1, 4, 3),
                new Square(3.3),
                new Square(3.2),
                new Rectangle(2.1, 3.2),
                new Rectangle(3.1, 2.2)
        };

        // посмотреть на все фигуры в консоли
        for (Shape e : shapes) {
            System.out.print(e.toString() + " - ");
            System.out.print("ширина: " + e.getWidth() + "; ");
            System.out.print("высота: " + e.getHeight() + "; ");
            System.out.print("площадь: " + e.getArea() + "; ");
            System.out.println("периметр: " + e.getPerimeter() + ".");
        }

        // todo поиск максимальной площадью и второй по периметру
        /*Часть 2.
        + В main в коде объявить массив фигур, чтобы в нём было около 5-10 разных фигур.
        ** Задача – написать функцию, которая находит фигуру с максимальной площадью.
                Вызвать её для этого массива и распечатать информацию о фигуре в консоль.
        ** Аналогично найдите фигуру со вторым по величине периметром.
        Поиск фигур реализовать через стандартный метод Arrays.sort (в C# Array.Sort) с компаратором.
        Что такое компаратор почитайте сами, но если будут вопросы, задавайте.
        */

        //Arrays.sort
        // todo разобрать что такое компаратор и зачем он тут нужен
    }
}
