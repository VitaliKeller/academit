import javax.crypto.spec.PSource;
import java.util.Arrays;

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
        return to > range2.getFrom() && range2.getTo() > from;
    }

    // пересечение отрезков.
    public Range getInterception(Range range2) {
        if (isIntercept(range2)) {
            return new Range(Math.max(from, range2.from), Math.min(to, range2.to));
        }

        return null;
    }

    // объединение отрезков
    public Range[] getUnion(Range range2) {
        if (to < range2.getFrom() || range2.getTo() < from) {
            return new Range[]{new Range(from, to), new Range(range2.getFrom(), range2.getTo())};
        }

        return new Range[]{new Range(Math.min(from, range2.getFrom()), Math.max(to, range2.getTo()))};
    }
}
