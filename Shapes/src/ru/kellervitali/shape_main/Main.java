package ru.kellervitali.shape_main;

import ru.kellervitali.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(2.5),
                new Circle(0.5),
                new Triangle(0, 0, 10, 0, 10, 1),
                new Triangle(3, 1, 2, 1, 4, 3),
                new Square(2.1),
                new Square(3.2),
                new Rectangle(2.1, 3.2),
                new Rectangle(2.1, 3.5)
        };

        // посмотреть на все фигуры в консоли
        print(shapes);

        /* Часть 2.
        В main в коде объявить массив фигур, чтобы в нём было около 5-10 разных фигур.
        * Задача - написать функцию, которая находит фигуру с максимальной площадью.
                 - вызвать её для этого массива и распечатать информацию о фигуре в консоль.
        * Аналогично найдите фигуру со вторым по величине периметром.

        Поиск фигур реализовать через стандартный метод Arrays.sort (в C# Array.Sort) с компаратором.
        Что такое компаратор почитайте сами, но если будут вопросы, задавайте.
        */

        Arrays.sort(shapes, new ShapeAreaComparator());
        print(shapes);
        System.out.println("========= 1-е место по площади: " + shapes[shapes.length - 1]);

        Arrays.sort(shapes, new ShapePerimeterComparator());
        print(shapes);
        System.out.println("========= 2-е место по периметру: " + shapes[shapes.length - 2]);
    }

    private static void print(Shape[] shapes) {
        System.out.println();

        for (Shape e : shapes) {
            System.out.print(e + ". ");
            System.out.print("Ширина: " + e.getWidth() + "; ");
            System.out.print("высота: " + e.getHeight() + "; ");
            System.out.print("площадь: " + e.getArea() + "; ");
            System.out.println("периметр: " + e.getPerimeter() + ".");
        }
    }
}
