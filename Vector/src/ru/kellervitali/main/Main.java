package ru.kellervitali.main;

import ru.kellervitali.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] vectorCoordinates1 = {1, 4, 2};
        double[] vectorCoordinates2 = {4, 2, 1, 5};

        Vector vector1 = new Vector(vectorCoordinates1);
        Vector vector2 = new Vector(vectorCoordinates2);
        Vector vector3 = new Vector(2);
        vector3.setCoordinates(new double[]{1, 2});
        Vector vector4 = new Vector(vector2);
        Vector vector5 = new Vector(new double[]{3,5,7},5);

        System.out.println("vector1 = " + vector1);
        System.out.println("vector2 = " + vector2);
        System.out.println("vector3 = " + vector3);
        System.out.println("vector4 = " + vector4);
        System.out.println("vector5 = " + vector5);
    }
}
