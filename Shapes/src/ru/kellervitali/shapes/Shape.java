package ru.kellervitali.shapes;

public interface Shape extends Comparable<Shape> {
    double getWidth();

    double getHeight();

    double getArea();

    double getPerimeter();

    @Override
    default int compareTo(Shape o) {
        return Double.compare(this.getArea(), o.getArea());
    }
}
