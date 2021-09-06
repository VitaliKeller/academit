package ru.vitalikeller.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*if (args.length != 1) {
            System.out.println("Программа конвертирует файл из формата CSV в исходящий файл, формата HTML");
            System.out.println("Для программы нужен один аргумент - путь и имя файла для конвертации");
            System.out.println("Исходящий HTML - будет выложен в папку по умолчанию.");

            return;
        }*/

        /*try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             PrintWriter writer = new PrintWriter("index.html")) {

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }*/
        String incomeFileName = "text.csv";
        System.out.println(incomeFileName);
        System.out.println("... Загрузка файла.");

        StringBuilder csvContent = new StringBuilder();

        try (Scanner scannerFile = new Scanner(
                new FileInputStream(incomeFileName), StandardCharsets.UTF_8); //args[0]
             PrintWriter writer = new PrintWriter("index.html")
        ) {
            while (scannerFile.hasNextLine()) {
                csvContent.append(scannerFile.nextLine()).append("\r\n");


            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл \"" + incomeFileName + "\" не найден.");  //args[0]
            System.out.println("Описание ошибки: " + e);
        }

        // ------- использование csvContent -----------
        System.out.println("... Конвертация в HTML, файла: " + incomeFileName);
        StringBuilder htmlContent = convertCsvToHtml(csvContent);

        String outFileName = "index.html";
        System.out.println("... Вывод в файл: " + outFileName);
        saveHtmlContentToFile(outFileName, htmlContent);    // args[1]

    }

    private static StringBuilder convertCsvToHtml(StringBuilder contentCSV) {
        StringBuilder contentHTML = new StringBuilder();

        contentHTML.append("<!DOCTYPE html>");
        contentHTML.append("<html>\r\n");
        contentHTML.append("    <head>\r\n");
        contentHTML.append("        <meta charset=\"utf-8\">");
        contentHTML.append("        <title>CSV to HTML</title>");
        contentHTML.append("    </head>\r\n");
        contentHTML.append("    <body>\r\n");
        contentHTML.append("<table border=\"1\">\r\n");

        boolean isTdOpen = false;

        for (String textLine : contentCSV.toString().split("(\r\n|\r|\n)", -1)) {
            // если ячейка не была открыта принудительно - открыть строку
            if (!isTdOpen) {
                contentHTML.append("  <tr>\r\n");
                contentHTML.append("    <td>");
            }

            char[] rowData = textLine.toCharArray();
            int length = rowData.length;

            for (int i = 0; i < length; ++i) {
                if (rowData[i] == ',' && !isTdOpen) {
                    contentHTML.append("</td>\r\n");
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
                contentHTML.append("</td>\r\n");
                contentHTML.append("  </tr>\r\n");
            } else {
                contentHTML.append("<br/>");
            }
        }

        contentHTML.append("</table>\r\n");
        contentHTML.append("    </body>\r\n");
        contentHTML.append("</html>\r\n");

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
