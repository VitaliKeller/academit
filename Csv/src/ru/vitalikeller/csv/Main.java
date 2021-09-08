package ru.vitalikeller.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Программа конвертирует файл из формата CSV в файл формата HTML.");
            System.out.println("Для программы нужно два аргумента:");
            System.out.println("1. путь и имя файла откуда брать CSV, ");
            System.out.println("2. путь и имя файла куда выложить результирующий файл HTML.");

            return;
        }

        System.out.println("... Загрузка CSV файла (" + args[0] + "), обработка, и выкладка в HTML (" + args[1] + ").");

        try (Scanner scanner = new Scanner(new FileInputStream(args[0]), StandardCharsets.UTF_8); //args[0]
             PrintWriter writer = new PrintWriter(args[1])
        ) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta charset=\"utf-8\">");
            writer.println("        <title>CSV to HTML</title>");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("<table border=\"1\">");

            boolean isTdOpen = false;

            while (scanner.hasNextLine()) {
                String textLine = scanner.nextLine();

                if (textLine.length() == 0) {
                    continue;
                }

                // если ячейка не была открыта принудительно - открыть строку
                if (!isTdOpen) {
                    writer.println("  <tr>");
                    writer.print("    <td>");
                }

                int length = textLine.length();

                for (int i = 0; i < length; ++i) {
                    if (textLine.charAt(i) == ',' && !isTdOpen) {
                        writer.println("</td>");
                        writer.print("    <td>");
                    } else if (isTdOpen && textLine.charAt(i) == '"' && i + 1 < length && textLine.charAt(i + 1) == '"') {
                        writer.print(textLine.charAt(i));
                        ++i;
                    } else if (textLine.charAt(i) == '"') {
                        isTdOpen = !isTdOpen;
                    } else if (textLine.charAt(i) == '<') {
                        writer.print("&lt;");
                    } else if (textLine.charAt(i) == '>') {
                        writer.print("&gt;");
                    } else if (textLine.charAt(i) == '&') {
                        writer.print("&amp;");
                    } else {
                        writer.print(textLine.charAt(i));
                    }
                }

                if (!isTdOpen) {
                    writer.println("</td>");
                    writer.println("  </tr>");
                } else {
                    writer.print("<br/>");
                }
            }

            writer.println("</table>");
            writer.println("    </body>");
            writer.println("</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
            System.out.println("Описание ошибки: " + e);
            System.out.println("Для корректной работы программы, требуется указать в строке вызова два аргумента:");
            System.out.println("Первый должен содержать полный адрес файла CSV для чтения, с указанием имени и расширения файла");
            System.out.println("Второй должен содержать полный адрес к файлу для записи HTML с указанием имени и расширения файла");
        }
    }
}
