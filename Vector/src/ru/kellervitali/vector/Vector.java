package ru.kellervitali.vector;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Vector {
    double[] coordinate;

    public Vector(double[] coordinate) {
        //int incomingLength = Array.getLength(coordinate);
        this.coordinate = coordinate;
    }

    public Vector(int dimention) {
        this.coordinate = new double[dimention];
    }


    @Override
    public String toString() {
        return "Vector{" +
                "coordinate=" + Arrays.toString(coordinate) +
                '}';
    }
}
