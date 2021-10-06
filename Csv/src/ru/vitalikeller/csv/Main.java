package ru.vitalikeller.csv;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Программа конвертирует файл из формата CSV в файл формата HTML.");
            System.out.println("Для программы нужно два аргумента:");
            System.out.println("1. путь и имя файла откуда брать CSV, ");
            System.out.println("2. путь и имя файла куда выложить результирующий файл HTML.");

            return;
        }

        String inputFilePath = args[0]; //"text.csv"
        String outputFilePath = args[1]; //"index.html"

        System.out.println("... Загрузка CSV файла (" + inputFilePath + "), обработка, и выкладка в HTML (" + outputFilePath + ").");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter writer = new PrintWriter(outputFilePath)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta charset=\"utf-8\">");
            writer.println("        <title>CSV to HTML</title>");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("<table border=\"1\">");

            boolean isTdOpen = false;

            String textLine;

            while ((textLine = bufferedReader.readLine()) != null) {    // https://metanit.com/java/tutorial/6.9.php
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
            System.out.println("Ошибка: " + e.getMessage());
            System.out.println("Для корректной работы программы, требуется указать в строке вызова два аргумента:");
            System.out.println("Первый должен содержать полный путь к файлу CSV для чтения, с указанием имени и расширения файла");
            System.out.println("Второй должен содержать полный путь к файлу для записи HTML с указанием имени и расширения файла");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода: " + e.getMessage());
        }
    }
}
