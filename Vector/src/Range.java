public class Range {
    // класс, описывающий отрезок. И его методы.
    private double from;    // начало отрезка, включительно
    private double to;      // конец отрезка, включительно

    public Range(double from, double to) {
        if (to < from) {        // проверка что to > from, при создании отрезка
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

    // проверка на вхождение числа в отрезок
    public boolean isInside(double numberToCheck) {
        return numberToCheck >= from && numberToCheck <= to;
    }

    // длина отрезка.
    public double getLength() {
        return to - from;
    }

    // проверка пересечения.
    public boolean isIntercept(Range range2) {
        return range2.getFrom() <= to && from <= range2.getTo();
    }

    // пересечение отрезков.
    public Range getInterception(Range range2) {
        if (isIntercept(range2)) {
            return new Range(Math.max(from, range2.from), Math.min(to, range2.to));
        } else {
            return null;
        }
    }

    // объединение отрезков
    public Range[] getUnion(Range range2) {
        return new Range[] {new Range(0, 0)};
    }
}
