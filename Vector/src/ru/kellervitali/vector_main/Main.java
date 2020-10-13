package ru.kellervitali.vector_main;

import ru.kellervitali.vector_class.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{-1.1, 4, 2});
        Vector vector2 = new Vector(new double[]{4, 2, 1, 5});
        Vector vector3 = new Vector(vector2);
        Vector vector4 = new Vector(new double[]{3, 5, 7}, 5);

        System.out.println(vector1 + ", размерность = " + vector1.getSize() + ", длина = " + vector1.getLength() + ", hash = " + vector1.hashCode());
        System.out.println(vector2 + ", размерность = " + vector2.getSize() + ", длина = " + vector2.getLength() + ", hash = " + vector2.hashCode());
        System.out.println(vector3 + ", размерность = " + vector3.getSize() + ", длина = " + vector3.getLength() + ", hash = " + vector3.hashCode());
        System.out.println(vector4 + ", размерность = " + vector4.getSize() + ", длина = " + vector4.getLength() + ", hash = " + vector4.hashCode());
        //System.out.println(vector5);

        try {
            Vector vector5 = new Vector(2);
            vector5.setCoordinates(new double[]{1, 2});
            vector5.setCoordinateByIndex(1, 5.5);
            System.out.println(vector5);

            Vector vector666 = new Vector(-1);
            System.out.println(vector666);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("4.a. Координаты сложения вектора 1 и 2: " + Arrays.toString(vector1.addVector(vector2)));
        System.out.println("4.b. Координаты вычитания из вектора 1 вектора 2: " + Arrays.toString(vector1.subtractVector(vector2)));
        System.out.println("4.c. Координаты умножения вектора 1 на скалар (3): " + Arrays.toString(vector1.scalarVector(3)));
        System.out.println("4.d. Координаты разворота вектора 1: " + Arrays.toString(vector1.reverseVector()));

        System.out.println();
        System.out.println("Проверка равенства векторов");
        System.out.println("(1 == 2) " + vector1.equals(vector2));
        System.out.println("(2 == 3) " + vector2.equals(vector3));
        System.out.println("(3 == 4) " + vector3.equals(vector4));
        System.out.println("(4 == 1) " + vector4.equals(vector1));

        System.out.println();
        System.out.println("5.a " + Vector.addVector(vector1, vector2));
        System.out.println("5.b " + Vector.subtractVector(vector1, vector2));
        System.out.println("5.c " + Vector.scalarVector(vector1, vector2));
    }
}
