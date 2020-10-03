import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начало (включительно) первого отрезка: ");
        double firstRangeFrom = scanner.nextDouble();

        System.out.println("Введите конец (включительно) первого отрезка: ");
        double firstRangeTo = scanner.nextDouble();
    }
}
