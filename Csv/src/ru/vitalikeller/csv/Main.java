package ru.vitalikeller.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Программа конвертирует файл из формата CSV в исходящий файл, формата HTML");
            System.out.println("Для программы нужен один аргумент - путь и имя файла для конвертации");
            System.out.println("Исходящий HTML - будет выложен в папку по умолчанию.");

            return;
        }

        /*try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             PrintWriter writer = new PrintWriter("index.html")) {

        }*/
        String fileName = "text.csv";
        System.out.println(fileName);

        System.out.println("... Загрузка файла.");
        StringBuilder csvContent = readCsvFile(fileName);

        System.out.println("... Конвертация в HTML, файла: " + fileName);
        StringBuilder htmlContent = convertCsvToHtml(csvContent);

        String outFileName = "index.html";
        System.out.println("... Вывод в файл: " + outFileName);
        saveHtmlContentToFile(outFileName, htmlContent);

    }

    private static StringBuilder readCsvFile(String fileName) {
        StringBuilder csvContent = new StringBuilder();
        try (Scanner scannerFile = new Scanner(new FileInputStream(fileName), StandardCharsets.UTF_8)) {
            while (scannerFile.hasNextLine()) {
                csvContent.append(scannerFile.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        return csvContent;
    }

    private static StringBuilder convertCsvToHtml(StringBuilder contentCSV) {
        StringBuilder contentHTML = new StringBuilder();

        contentHTML.append("<!DOCTYPE html>");
        contentHTML.append("<html>\n");
        contentHTML.append("    <head>\n");
        contentHTML.append("        <meta charset=\"utf-8\">");
        contentHTML.append("        <title>CSV to HTML</title>");
        contentHTML.append("    </head>\n");
        contentHTML.append("    <body>\n");
        contentHTML.append("<table border=\"1\">\n");

        boolean isTdOpen = false;

        for (String row : contentCSV.toString().split("\\n")) {
            // если ячейка не была открыта принудительно - открыть строку
            if (!isTdOpen) {
                contentHTML.append("  <tr>\n");
                contentHTML.append("    <td>");
            }

            char[] rowData = row.toCharArray();
            int length = rowData.length;

            for (int i = 0; i < length; ++i) {
                if (rowData[i] == ',' && !isTdOpen) {
                    contentHTML.append("</td>\n");
                    contentHTML.append("    <td>");
                } else if (isTdOpen && rowData[i] == '"' && i + 1 < length && rowData[i + 1] == '"') {
                    contentHTML.append(rowData[i]);
                    ++i;
                } else if (rowData[i] == '"') {
                    isTdOpen = !isTdOpen;
                } else if (rowData[i] == '<') {
                    contentHTML.append("&lt;");
                } else if (rowData[i] == '>') {
                    contentHTML.append("&gt;");
                } else if (rowData[i] == '&') {
                    contentHTML.append("&amp;");
                } else {
                    contentHTML.append(rowData[i]);
                }
            }

            if (!isTdOpen) {
                contentHTML.append("</td>\n");
                contentHTML.append("  </tr>\n");
            } else {
                contentHTML.append("<br/>");
            }
        }

        contentHTML.append("</table>\n");
        contentHTML.append("    </body>\n");
        contentHTML.append("</html>\n");

        return contentHTML;
    }

    private static void saveHtmlContentToFile(String outFileName, StringBuilder htmlContent) {
        try (PrintWriter writer = new PrintWriter(outFileName)) {
            writer.print(htmlContent);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
