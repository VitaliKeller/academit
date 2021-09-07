package ru.vitalikeller.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        String[] args = new String[2];

        args[0] = "text.csv";
        if (args[1] == null) {
            args[1] = "index.html";
        }

        if (args.length != 2) {
            System.out.println("Программа конвертирует файл из формата CSV в исходящий файл, формата HTML");
            System.out.println("Для программы нужен один аргумент - путь и имя файла для конвертации");
            System.out.println("Или укажите два аргумента - откуда брать CSV, и куда выложить результирующий файл HTML");
            System.out.println("Если второй аргумент не указан - исходящий HTML будет выложен в папку по умолчанию.");

            return;
        }

        System.out.println(args[0] + " ... Загрузка файла.");

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
                String textLineString = scanner.nextLine();

                // если ячейка не была открыта принудительно - открыть строку
                if (!isTdOpen) {
                    writer.println("  <tr>");
                    writer.println("    <td>");
                }

                int length = textLineString.length();

                for (int i = 0; i < length; ++i) {
                    if (textLineString.charAt(i) == ',' && !isTdOpen) {
                        writer.print("</td>");
                        writer.print("    <td>");
                    } else if (isTdOpen && textLineString.charAt(i) == '"' && i + 1 < length && textLineString.charAt(i + 1) == '"') {
                        writer.print(textLineString.charAt(i));
                        ++i;
                    } else if (textLineString.charAt(i) == '"') {
                        isTdOpen = !isTdOpen;
                    } else if (textLineString.charAt(i) == '<') {
                        writer.print("&lt;");
                    } else if (textLineString.charAt(i) == '>') {
                        writer.print("&gt;");
                    } else if (textLineString.charAt(i) == '&') {
                        writer.print("&amp;");
                    } else {
                        writer.print(textLineString.charAt(i));
                    }
                }

                if (!isTdOpen) {
                    writer.println("</td>");
                    writer.println("  </tr>");
                } else {
                    writer.println("<br/>");
                }
            }

            writer.println("</table>");
            writer.println("    </body>");
            writer.println("</html>");

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
            System.out.println("Описание ошибки: " + e);
        }
    }
}
