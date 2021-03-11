package ru.kellervitali;

public class Car implements Comparable<Car> {
    private int manufactureYear;
    private String model;
    private int maxSpeed;

    public Car(int manufactureYear, String model, int maxSpeed) {
        this.manufactureYear = manufactureYear;
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public int compareTo(Car o) {
        return this.manufactureYear - o.manufactureYear;
    }

    @Override
    public String toString() {
        //return model + ", год: " + manufactureYear;
        return "Car{manufactureYear=" + manufactureYear + ", model=" + model + ", maxSpeed=" + maxSpeed + "}";
    }
}
