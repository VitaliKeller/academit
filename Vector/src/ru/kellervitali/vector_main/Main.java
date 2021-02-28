package ru.kellervitali.vector_main;

import ru.kellervitali.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{-1.1, 4, 2});
        Vector vector2 = new Vector(new double[]{4, 2, 1, 5});
        Vector vector3 = new Vector(vector2);
        Vector vector4 = new Vector(6, new double[]{3, 4, 7});

        System.out.println(vector1 + ", размерность = " + vector1.getSize() + ", длина = " + vector1.getLength() + ", hash = " + vector1.hashCode());
        System.out.println(vector2 + ", размерность = " + vector2.getSize() + ", длина = " + vector2.getLength() + ", hash = " + vector2.hashCode());
        System.out.println(vector3 + ", размерность = " + vector3.getSize() + ", длина = " + vector3.getLength() + ", hash = " + vector3.hashCode());
        System.out.println(vector4 + ", размерность = " + vector4.getSize() + ", длина = " + vector4.getLength() + ", hash = " + vector4.hashCode());
        //System.out.println(vector5);

        try {
            Vector vector5 = new Vector(2);
            vector5.setCoordinateByIndex(1, 5.5);
            System.out.println(vector5);

            Vector vector666 = new Vector(-1);
            System.out.println(vector666);
        } catch (Throwable e) {
            System.out.println("проверка ошибки - " + e.getMessage());
        }

        System.out.println();
        System.out.println("4.a. Координаты сложения вектора 2 и 1: " + vector2.getAddingResult(vector1));
        System.out.println("4.a. Координаты сложения вектора 1 и 2: " + vector1.getAddingResult(vector2));
        System.out.println("4.b. Координаты вычитания из вектора 2 вектора 1: " + vector2.getSubtractingResult(vector1));
        System.out.println("4.b. Координаты вычитания из вектора 1 вектора 2: " + vector1.getSubtractingResult(vector2));
        System.out.println("4.c. Координаты умножения вектора 1 на скалар (3): " + vector1.getScalarMultiply(3));
        System.out.println("4.d. Координаты разворота вектора 1: " + vector1.reverse());

        System.out.println();
        System.out.println("Проверка равенства векторов");
        System.out.println("(1 == 2) " + vector1.equals(vector2));
        System.out.println("(2 == 3) " + vector2.equals(vector3));
        System.out.println("(3 == 4) " + vector3.equals(vector4));
        System.out.println("(4 == 1) " + vector4.equals(vector1));

        System.out.println();
        System.out.println("5.a " + Vector.getAddingResult(vector1, vector2));
        System.out.println("5.b " + Vector.getSubtractingResult(vector1, vector2));
        System.out.println("5.c " + Vector.getScalarMultiply(vector1, vector2));
    }
}
