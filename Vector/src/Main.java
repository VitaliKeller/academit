import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!");

        // первый отрезок
        System.out.println("Введите начало (включительно) первого отрезка: ");
        double from = scanner.nextDouble();
        System.out.println("Введите конец (включительно) первого отрезка: ");
        double to = scanner.nextDouble();

        Range range1 = new Range(from,to);


        // число для проверки вхождения
        System.out.println("Введите число, для проверки его вхождения в отрезок: ");
        double numberToChek = scanner.nextDouble();

        // второй отрезок
        System.out.println("Введите начало (включительно) второго отрезка: ");
        from = scanner.nextDouble();
        System.out.println("Введите конец (включительно) второго отрезка: ");
        to = scanner.nextDouble();

        Range range2 = new Range(from,to);

    }
}
