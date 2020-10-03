public class Range {
    // Класс отрезок. И его методы.
    private double from;        // Начало отрезка, включительно
    private double to;          // Конец отрезка, включительно

    public Range(double from, double to) {
        if (to < from) {        // Проверка что to > from, при создании отрезка
            this.from = to;
            this.to = from;
        }

        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
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
    public boolean isInside(double numberToCheck) {
        return numberToCheck >= from && numberToCheck <= to;
    }

    // Длина отрезка.
    public double getLength() {
        return to - from;
    }

    // Проверка пересечения boolean.
    public boolean isIntercept(Range range2) {
        return to > range2.getFrom() && range2.getTo() > from;
    }

    // Пересечение (общая часть) отрезков.
    public Range getInterception(Range range2) {
        if (isIntercept(range2)) {
            return new Range(Math.max(from, range2.from), Math.min(to, range2.to));
        }

        return null;
    }

    // Объединение отрезков
    public Range[] getUnion(Range range2) {
        if (to < range2.getFrom() || range2.getTo() < from) {
            return new Range[]{new Range(from, to), new Range(range2.getFrom(), range2.getTo())};
        }

        return new Range[]{new Range(Math.min(from, range2.getFrom()), Math.max(to, range2.getTo()))};
    }

    // Вычесть из первого отрезка второй
    public Range[] getDifference(Range range2) {
        double from1 = from, to1 = to, from2 = range2.getFrom(), to2 = range2.getTo();

        if (from1 < from2 && to1 > to2) {           // отрезок 2 внутри 1
            return new Range[]{new Range(from1, from2), new Range(to2, to1)};
        }

        if (from1 > from2 && to1 < to2) {           // отрезок 1й перекрыт 2ым
            return new Range[]{};
        }

        if (from1 < from2 && to1 > from2) {         // частично пересекаются, 1й начало и конец левее
            return new Range[]{new Range(from1, from2)};
        }

        if (from2 < from1 && to2 > from1) {         // частично пересекаются, 1й начало и конец правее
            return new Range[]{new Range(to2, to1)};
        }

        return new Range[]{new Range(from1, to1)};  // не пересекаются
    }
}
