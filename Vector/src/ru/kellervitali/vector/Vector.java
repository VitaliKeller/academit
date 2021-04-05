package ru.kellervitali.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    // 1.a. Vector(n) – размерность n, все компоненты равны
    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Передан size = " + size + ". (size должно быть > 0!)");
        }

        coordinates = new double[size];
    }

    // 1.b. Vector(Vector) – конструктор копирования
    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    // 1.c. Vector(double[]) – заполнение вектора значениями из массива
    public Vector(double[] coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("Количество компонент (координат) вектора должно быть > 0!");
        }

        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
    }

    // 1.Vector(n, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
    public Vector(int size, double[] coordinates) {
        if (size <= 0) {
            throw new IllegalArgumentException("Передана size = " + size + ". (size должно быть > 0!)");
        }

        this.coordinates = Arrays.copyOf(coordinates, size);
    }

    // ================== МЕТОДЫ

    // 2. Метод getSize() для получения размерности вектора
    public int getSize() {
        return coordinates.length;
    }

    // 3. Реализовать метод toString(), чтобы выдавал информацию о векторе в формате { значения компонент через запятую }
    @Override
    public String toString() {
        // переделал на stringBuilder, чтобы убрать warning
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (double coordinate : coordinates) {
            stringBuilder.append(coordinate).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    // 4.a. Прибавление к вектору другого вектора
    public Vector add(Vector vector) {
        if (vector.coordinates.length > coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; i++) {
            coordinates[i] += vector.coordinates[i];
        }

        return this;
    }

    // 4.b. Вычитание из вектора другого вектора
    public Vector subtract(Vector vector) {
        if (vector.coordinates.length > coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; i++) {
            coordinates[i] -= vector.coordinates[i];
        }

        return this;
    }

    //  4.c. Умножение вектора на скаляр
    public Vector multiplyByNumber(double number) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= number;
        }

        return this;
    }

    //  4.d. Разворот вектора (умножение всех компонент на -1)
    public Vector reverse() {
        return multiplyByNumber(-1);
    }

    // 4.e Получение длины вектора
    // формула - https://zaochnik.com/spravochnik/matematika/vektory/dlina_vectora/
    public double getLength() {
        double sum = 0;

        for (double e : coordinates) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    // 4.f.1 Получение компоненты вектора по индексу
    public double getCoordinateByIndex(int index) {
        if (index < 0 || index >= coordinates.length) {
            throw new IndexOutOfBoundsException("Передано неверное значение индекса = " + index + " (Размерность вектора = " + coordinates.length + ").");
        }

        return coordinates[index];
    }

    // 4.f.2 Установка компоненты вектора по индексу
    public void setCoordinateByIndex(int index, double coordinateValue) {
        if (index < 0 || index >= coordinates.length) {
            throw new IndexOutOfBoundsException("Передано неверное значение индекса = " + index + " (Размерность вектора = " + coordinates.length + ").");
        }

        coordinates[index] = coordinateValue;
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
        return Arrays.hashCode(coordinates);
    }

    // 5.a. Сложение двух векторов – должен создаваться новый вектор
    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);

        resultVector.add(vector2);

        return resultVector;
    }

    // 5.b. Вычитание векторов – должен создаваться новый вектор
    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);

        resultVector.subtract(vector2);

        return resultVector;
    }

    // 5.c. Скалярное произведение векторов
    public static double getProduct(Vector vector1, Vector vector2) {
        int minVectorsSize = Math.min(vector1.coordinates.length, vector2.coordinates.length);
        double result = 0;

        for (int i = 0; i < minVectorsSize; i++) {
            result += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return result;
    }
}
