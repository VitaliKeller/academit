package ru.kellervitali.vector;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Vector {
    int coordinate[];

    public Vector(int[] coordinate) {
        int incomingLength = Array.getLength(coordinate);
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "coordinate=" + Arrays.toString(coordinate) +
                '}';
    }
}
