public class Range {
    // класс, описывающий отрезхок. И его методы.
    // при создании отрезка НЕ проверяется что from < to. Согласно ТЗ.
    private double from;    // начало отрезка, включительно
    private double to;      // конец отрезка, включительно

    public Range(double from, double to) {
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
        return "Range{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    public boolean isNumberInsideThisRage(double numberToCheck) {
        return numberToCheck >= from && numberToCheck <= to;
    }
}
