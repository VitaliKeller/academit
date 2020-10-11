package ru.kellervitali.vector_class;

import java.util.Arrays;

public class Vector {
    // ================= ПОЛЯ
    private final String vectorName;
    private double[] coordinates;

    // ================= КОНСТРУКТОРЫ
    // 1.a. Vector(n) – размерность n, все компоненты равны
    public Vector(String vectorName, int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Ошибка! dimension должно быть \">0\"!");
        }

        this.vectorName = vectorName;
        this.coordinates = new double[dimension];
    }

    // 1.b. Vector(Vector) – конструктор копирования
    public Vector(String vectorName, Vector vector) {
        //this.coordinates = new double[]{vector.getCoordinates()}; // todo так не работает... Разобрать как правильно предать, неужели каждую отдельно?
        this.vectorName = vectorName;
        this.coordinates = Arrays.copyOf(vector.getCoordinates(), vector.getCoordinates().length);
    }

    public Vector(String vectorName, double[] coordinates) {    // 1.c. Vector(double[]) – заполнение вектора значениями из массива
        this.vectorName = vectorName;
        this.coordinates = coordinates;
    }

    // 1.Vector(n, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
    public Vector(String vectorName, double[] coordinates, int dimension) {
        this.vectorName = vectorName;
        this.coordinates = Arrays.copyOf(coordinates, dimension);
    }

    // ================== МЕТОДЫ
    // Метод getSize() для получения размерности вектора
    public int getSize() {
        return coordinates.length;
    }

    // Реализовать метод toString(), чтобы выдавал информацию о векторе в формате { значения компонент через запятую }
    @Override
    public String toString() {      // 3.
        //return vectorName + Arrays.toString(coordinates);
        String response = "{";
        String actualDelimiter;

        for (int i = 0; i < coordinates.length; i++
        ) {
            if (i < coordinates.length - 1) {
                actualDelimiter = ", ";
            } else {
                actualDelimiter = "}";
            }

            response = response + String.valueOf(coordinates[i]) + actualDelimiter;
        }

        return vectorName + response;
    }

    // 4.e Получение длины вектора
    // формула - https://zaochnik.com/spravochnik/matematika/vektory/dlina_vectora/
    public double getLength() {
        double powSum = 0;

        for (double e : coordinates
        ) {
            powSum += Math.pow(e, 2);
        }

        return Math.abs(Math.sqrt(powSum));
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
