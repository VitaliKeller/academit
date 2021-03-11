package ru.kellervitali;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Compare_main {
    public static void main(String[] args) {

        String name1 = "Маша";
        String name2 = "Саша";
        String name3 = "Даша";

        List<String> names = new ArrayList<>();
        names.add(name1);
        names.add(name2);
        names.add(name3);

        Collections.sort(names);
        System.out.println(names);

        // https://javarush.ru/groups/posts/2262-comparator-v-java

        List<Car> cars = new ArrayList<>();

        Car ferrari = new Car(1990, "Ferrari 360 Spider", 310);
        Car lambo = new Car(2012, "Lamborghini Gallardo", 290);
        Car bugatti = new Car(2010, "Bugatti Veyron", 350);

        cars.add(ferrari);
        cars.add(bugatti);
        cars.add(lambo);

        Collections.sort(cars);
        System.out.println(cars);

        cars.sort(new MaxSpeedCarComparator());
        System.out.println(cars);
    }
}
