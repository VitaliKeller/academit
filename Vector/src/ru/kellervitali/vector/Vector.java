package ru.kellervitali.vector;

import java.util.Arrays;

public class Vector {
    // Поля объекта
    private double[] coordinates;

    // Конструкторы
    public Vector(int dimension) {      // 1.a
        this.coordinates = new double[dimension];
    }

    public Vector(Vector vector) {    // 1.b
        //this.coordinates = new double[]{vector.getCoordinates()}; // todo так не работает... Разобрать как правильно предать, неужели каждую отдельно?
        this.coordinates = Arrays.copyOf(vector.getCoordinates(), vector.getCoordinates().length);
    }

    public Vector(double[] coordinates) {    // 1.c
        this.coordinates = coordinates;
    }

    public Vector(double[] coordinates, int dimension) {    // 1.d
        this.coordinates = Arrays.copyOf(coordinates, dimension);
    }

    // Методы
    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {      // 3.
        return "Vector" + Arrays.toString(coordinates);
    }


}
