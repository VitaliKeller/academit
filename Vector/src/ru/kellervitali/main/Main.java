package ru.kellervitali.main;

import ru.kellervitali.vector.Vector;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int vectorCoordinates1[] = {1,4,2};
        int vectorCoordinates2[] = {4,2,1,5};

        System.out.println(Arrays.toString(vectorCoordinates1));

        Vector vector1 = new Vector(vectorCoordinates1);
        System.out.println();
    }
}
