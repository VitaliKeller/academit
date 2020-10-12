package ru.kellervitali.range_class;

public class Range {
    // Класс отрезок. И его методы.
    private double from;        // Начало отрезка, включительно
    private double to;          // Конец отрезка, включительно

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    @Override
    public String toString() {
        if (from == to) {
            return "[" + from + "]";
        }

        return "[" + from + ", " + to + "]";
    }

    // Проверка на вхождение числа в отрезок
    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    // Длина отрезка.
    public double getLength() {
        return to - from;
    }

    // Проверка пересечения boolean.
    public boolean isIntercept(Range range) {
        return to > range.getFrom() && range.getTo() > from;
    }

    // Пересечение (общая часть) отрезков.
    public Range getIntersection(Range range) {
        if (isIntercept(range)) {
            return new Range(Math.max(from, range.from), Math.min(to, range.to));
        }

        return null;
    }

    // Объединение отрезков
    public Range[] getUnion(Range range) {
        if (to < range.getFrom() || range.getTo() < from) {
            return new Range[]{new Range(from, to), new Range(range.getFrom(), range.getTo())};
        }

        return new Range[]{new Range(Math.min(from, range.getFrom()), Math.max(to, range.getTo()))};
    }

    // Вычесть из первого отрезка второй
    public Range[] getDifference(Range range) {
        double from1 = from;
        double to1 = to;
        double from2 = range.getFrom();
        double to2 = range.getTo();

        if (from1 >= to2 || from2 >= to1) {
            return new Range[]{new Range(from1, to1)};
        }                                               // не пересекаются

        if (from1 < from2 && to1 > to2) {               // отрезок 2 внутри 1 - получаем 2 отрезка
            return new Range[]{new Range(from1, from2), new Range(to2, to1)};
        }

        if (from1 >= from2 && to1 <= to2) {             // отрезок 1й перекрыт 2ым - пустой результат
            return new Range[]{};
        }

        if (from1 < from2) {                            // частично пересекаются, 1й начало < 2й начало. Непересечение отрезков - уже было проверено первым.
            return new Range[]{new Range(from1, from2)};
        }

        return new Range[]{new Range(to2, to1)};        // частично пересекаются, to1 > to2. Непересечение отрезков - уже было проверено первым.
    }
}
