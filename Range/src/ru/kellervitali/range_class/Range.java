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
        return to > range.from && range.to > from;
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
        if (to < range.from || range.to < from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    // Вычесть из первого отрезка второй
    public Range[] getDifference(Range range) {
        if (from >= range.to || range.from >= to) {
            return new Range[]{new Range(from, to)};
        }                                                       // не пересекаются

        if (from < range.from && to > range.to) {               // отрезок 2 внутри 1 - получаем 2 отрезка
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (from >= range.from && to <= range.to) {             // отрезок 1й перекрыт 2ым - пустой результат
            return new Range[]{};
        }

        if (from < range.from) {                                // частично пересекаются, 1й начало < 2й начало. Не-пересечение отрезков - уже было проверено первым.
            return new Range[]{new Range(from, range.from)};
        }

        return new Range[]{new Range(range.to, to)};            // частично пересекаются, to1 > to2. Не-пересечение отрезков - уже было проверено первым.
    }
}
