package ru.kellervitali;

public class Car implements Comparable<Car>{
    private int manufactureYear;
    private String model;
    private int maxSpeed;

    public Car(int manufactureYear, String model, int maxSpeed) {
        this.manufactureYear = manufactureYear;
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public int compareTo(Car o) {
        return this.manufactureYear - o.manufactureYear;
    }


}
