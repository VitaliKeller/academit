package ru.kellervitali.vector_class;

import java.util.Arrays;

public class Vector {
    // ================= ПОЛЯ

    private double[] coordinates;

    // ================= КОНСТРУКТОРЫ

    // 1.a. Vector(n) – размерность n, все компоненты равны
    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Ошибка! dimension должно быть \">0\"!");
        }

        this.coordinates = new double[dimension];
    }

    // 1.b. Vector(Vector) – конструктор копирования
    public Vector(Vector vector) {
        this.coordinates = Arrays.copyOf(vector.getCoordinates(), vector.getCoordinates().length);
    }

    // 1.c. Vector(double[]) – заполнение вектора значениями из массива
    public Vector(double[] coordinates) {
        this.coordinates = coordinates;
    }

    // 1.Vector(n, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
    public Vector(double[] coordinates, int dimension) {
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

        return response;
    }

    // 4.a. Прибавление к вектору другого вектора
    public double[] addVector(Vector vector) {
        int size = Math.max(this.getSize(), vector.getSize());
        double[] addVectorResult = new double[size];

        for (int i = 0; i < size; i++) {
            addVectorResult[i] = this.getCoordinateByIndex(i) + vector.getCoordinateByIndex(i);
        }

        return addVectorResult;
    }

    // 4.b. Вычитание из вектора другого вектора
    public double[] subtractVector(Vector vector) {
        int size = Math.max(this.getSize(), vector.getSize());
        double[] addVectorResult = new double[size];

        for (int i = 0; i < size; i++) {
            addVectorResult[i] = this.getCoordinateByIndex(i) - vector.getCoordinateByIndex(i);
        }

        return addVectorResult;
    }

    //  4.c. Умножение вектора на скаляр
    public double[] scalarVector(Vector vector, double scalarValue) {
        double[] addVectorResult = new double[vector.getSize()];

        for (int i = 0; i < vector.getSize(); i++) {
            addVectorResult[i] = this.getCoordinateByIndex(i) * scalarValue;
        }

        return addVectorResult;
    }

    //  4.d. Разворот вектора (умножение всех компонент на -1)
    public double[] reverceVector(Vector vector) {
        double[] reverceVectorResult = new double[vector.getSize()];

        for (int i = 0; i < vector.getSize(); i++) {
            reverceVectorResult[i] = this.getCoordinateByIndex(i) * -1;
        }

        return reverceVectorResult;
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
    public double getCoordinateByIndex(int coordinateIndex) {
        if (coordinateIndex >= this.coordinates.length) {
            return 0;
        }

        return this.coordinates[coordinateIndex];
    }

    // 4.f.2 Установка компоненты вектора по индексу
    public void setCoordinateByIndex(int coordinateIndex, double coordinateValue) {
        if (coordinateIndex >= this.coordinates.length) {
            this.coordinates = Arrays.copyOf(this.coordinates, coordinateIndex);
        }

        this.coordinates[coordinateIndex] = coordinateValue;
    }

    // установить сразу все координаты
    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    // получить сразу все координаты
    public double[] getCoordinates() {
        return coordinates;
    }

    // 4.g Переопределить метод equals, чтобы был true ó векторы имеют одинаковую размерность и соответствующие компоненты равны.
    // Соответственно, переопределить hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return Arrays.equals(coordinates, vector.coordinates);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        return prime + Arrays.hashCode(coordinates);
    }

    // 5.a. Сложение двух векторов – должен создаваться новый вектор
    public static Vector addVector(Vector vector1, Vector vector2) {
        Vector newResultVector = new Vector(vector1);

        newResultVector.addVector(vector2);
        return newResultVector;
    }

    // 5.b. Вычитание векторов – должен создаваться новый вектор
    public static Vector subtractVector(Vector vector1, Vector vector2) {
        Vector newResultVector = new Vector(vector1);

        newResultVector.subtractVector(vector2);
        return newResultVector;
    }

    // 5.c. Скалярное произведение векторов
    public static double scalarVector(Vector vector1, Vector vector2) {
        int size = Math.min(vector1.getSize(), vector2.getSize());
        double scalarResult = 0;

        for (int i = 0; i < size; i++) {
            scalarResult = +vector1.getCoordinateByIndex(i) * vector2.getCoordinateByIndex(i);
        }

        return scalarResult;
    }
}
