package ru.kellervitali.main;

import ru.kellervitali.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[] {1, 4, 2});
        Vector vector2 = new Vector(new double[] {4, 2, 1, 5});
        Vector vector3 = new Vector(vector2);
        Vector vector4 = new Vector(new double[]{3, 5, 7}, 5);

        System.out.println("vector1 = " + vector1);
        System.out.println("vector2 = " + vector2);
        System.out.println("vector3 = " + vector3);
        System.out.println("vector4 = " + vector4);
        //System.out.println("vector5 = " + vector5);

        try {
            Vector vector5 = new Vector(2);
            vector5.setCoordinates(new double[]{1, 2});
            System.out.println("vector5 = " + vector5);
            Vector vector666 = new Vector(-1);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}
