package ru.kellervitali.vector;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Vector {
// Поля объекта
    double[] coordinates;

// Конструкторы
    public Vector(double[] coordinate) {
        //int incomingLength = Array.getLength(coordinate);
        this.coordinates = coordinate;
    }

    public Vector(int dimension) {
        this.coordinates = new double[dimension];
    }

// Методы
    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Vector" + Arrays.toString(coordinates);
    }


}
