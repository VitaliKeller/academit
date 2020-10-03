import java.util.Scanner;

public class Main {
    /*
        Часть 1. Создать класс Range (непрерывный вещественный числовой диапазон на прямой). В нём:
                1.1 Объявить два вещественных поля from, to
                1.2 Описать конструктор, при помощи которого заполняются поля
                1.3 Реализовать геттеры и сеттеры для полей
                1.4 Сделать метод для получения длины диапазона
                1.5 Сделать метод isInside, который принимает вещественное число и возвращает boolean – результат проверки, принадлежит ли число диапазону
            После этого написать небольшую программу с использованием этого класса.
            Java - любой класс должен лежать в некотором пакете.

        Часть 2 (Range*). Доработать класс Range, реализовать методы:
                2.1 Получение интервала-пересечения двух интервалов. Если пересечения нет, выдать null. Если есть, то выдать новый диапазон с соответствующими концами
                2.2 Получение объединения двух интервалов. Может получиться 1 или 2 отдельных куска
                2.3 Получение разности двух интервалов. Может получиться 0, 1 или 2 отдельных куска

            В операциях, где может получиться 2 куска, нужно выдавать массив объектов Range.
            Операции пересечения, объединения и разности – по смыслу такие же как для множеств, см. литературу по множествам. Разность нужна несимметричная – из первого интервала вычитаем второй.
            Эти методы нужно сделать нестатическими.

            В пересечении и разности если диапазоны пересекаются только по 1 концу, считаем, что пересечения нет. В объединении что есть. Так нужно сделать, чтобы результаты получались более логичными,
                т.к. в этой задаче мы не учитываем входит конец в диапазон или нет.
            В main написать программу для проверки этих методов.

    */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // (1.1, 1.2, 1.3) ---------- Задать отрезки
        Range range1 = new Range(10.5, 20.5);
        Range range2 = new Range(20.0, 25.0);

        // (1.4) ---------- Длина отрезка
        System.out.println("Длина отрезка №1 " + range1.toString() + " = " + range1.getLength());
        System.out.println("Длина отрезка №2 " + range2.toString() + " = " + range2.getLength());

        // (1.5) ---------- Проверка вхождения числа в отрезок
        System.out.print("*** Введите число, для проверки его вхождения в отрезки №1 и №2: ");
        double numberToChek = scanner.nextDouble();     // double numberToChek = 15.2

        System.out.println( numberToChek + " входит в " + range1.toString() + " = " + range1.isInside(numberToChek));
        System.out.println( numberToChek + " входит в " + range2.toString() + " = " + range2.isInside(numberToChek));

        // (2.1) ---------- пресечение отрезков
        if (range1.getInterception(range2) != null) {
            System.out.println("Пересечение отрезка " + range1.toString() + " и " + range2.toString() + " = " + range1.getInterception(range2).toString());
        } else {
            System.out.println("Пересечение отрезка " + range1.toString() + " и " + range2.toString() + " = ПУСТО!");
        }

/*
        System.out.print("Введите начало (включительно) первого отрезка: ");
        double from = scanner.nextDouble();
        System.out.print("Введите конец (включительно) первого отрезка: ");
        double to = scanner.nextDouble();

        System.out.print("Введите начало (включительно) второго отрезка: ");
        from = scanner.nextDouble();
        System.out.print("Введите конец (включительно) второго отрезка: ");
        to = scanner.nextDouble();
*/
    }
}
