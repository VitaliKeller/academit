package ru.kellervitali.vector_main;

import ru.kellervitali.vector_class.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector("vector1", new double[]{-1.1, 4, 2});
        Vector vector2 = new Vector("vector2", new double[]{4, 2, 1, 5});
        Vector vector3 = new Vector("vector3", vector2);
        Vector vector4 = new Vector("vector4", new double[]{3, 5, 7}, 5);

        System.out.println(vector1 + ", размерность = " + vector1.getSize() + ", длина = " + vector1.getLength());
        System.out.println(vector2 + ", размерность = " + vector2.getSize() + ", длина = " + vector2.getLength());
        System.out.println(vector3 + ", размерность = " + vector3.getSize() + ", длина = " + vector3.getLength());
        System.out.println(vector4 + ", размерность = " + vector4.getSize() + ", длина = " + vector4.getLength());
        //System.out.println(vector5);

        try {
            Vector vector5 = new Vector("vector5", 2);
            vector5.setCoordinates(new double[]{1, 2});
            vector5.setCoordinate(1, 5.5);
            System.out.println(vector5);

            Vector vector666 = new Vector("vector666", -1);
            System.out.println(vector666);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Координаты сложения вектора 1 и 2: " + Arrays.toString(vector1.addVector(vector2)));
    }
}
