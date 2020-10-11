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
        this.vectorName = vectorName;
        this.coordinates = Arrays.copyOf(vector.getCoordinates(), vector.getCoordinates().length);
    }

    // 1.c. Vector(double[]) – заполнение вектора значениями из массива
    public Vector(String vectorName, double[] coordinates) {
        this.vectorName = vectorName;
        this.coordinates = coordinates;
    }

    // 1.Vector(n, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
    public Vector(String vectorName, double[] coordinates, int dimension) {
        this.vectorName = vectorName;
        this.coordinates = Arrays.copyOf(coordinates, dimension);
    }

    // ================== МЕТОДЫ

    // 2. Метод getSize() для получения размерности вектора
    public int getSize() {
        return coordinates.length;
    }

    @Override
    // 3. Реализовать метод toString(), чтобы выдавал информацию о векторе в формате { значения компонент через запятую }
    public String toString() {
        //return vectorName + Arrays.toString(coordinates);
        String response = "{";
        String actualDelimiter;

        for (int i = 0; i < coordinates.length; i++) {
            if (i < coordinates.length - 1) {
                actualDelimiter = ", ";
            } else {
                actualDelimiter = "}";
            }

            response = response + String.valueOf(coordinates[i]) + actualDelimiter;
        }

        return vectorName + response;
    }

    //4.a. Прибавление к вектору другого вектора
    public double[] addVector(Vector vector) {
        int size = Math.max(this.getSize(), vector.getSize());
        double[] addVectorResult= new double[size];

        for (int i = 0; i < size; i++) {
            addVectorResult[i] = this.getCoordinate(i) + vector.getCoordinate(i);
        }

        return addVectorResult;
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

    // 4.f.1 Получение компоненты вектора по индексу
    public double getCoordinate(int coordinateIndex) {
        if (coordinateIndex >= this.coordinates.length) {
            return 0;
        }

        return this.coordinates[coordinateIndex];
    }

    // 4.f.2 Установка компоненты вектора по индексу
    public void setCoordinate(int coordinateIndex, double coordinateValue) {
        if (coordinateIndex >= this.coordinates.length) {
            // ---------------;
        }

        this.coordinates[coordinateIndex] = coordinateValue;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double[] getCoordinates() {
        return coordinates;
    }
}
