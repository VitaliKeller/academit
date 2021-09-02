package ru.vitalikeller.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);

        System.out.print("Введите название файла CSV: ");
        //String fileName = scanner.nextLine();
        String fileName = "text.csv";
        System.out.println(fileName);

        Run(fileName);
    }

    private static void Run(String fileName) {
        System.out.println("... Загрузка файла.");
        StringBuilder csvContent = readCsvFile(fileName);

        System.out.println("... Конвертация в HTML файла: " + fileName);
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

        contentHTML.append("<html>\n");
        contentHTML.append("    <head> CSV to HTML </head>\n");
        contentHTML.append("    <body>\n");
        contentHTML.append("<table border=\"1\">\n");

        boolean tdIsOpen = false;

        for (String row : contentCSV.toString().split("\\n")) {
            // если ячейка не была открыта принудительно - открыть строку
            if (!tdIsOpen) {
                contentHTML.append("  <tr>\n");
                contentHTML.append("    <td>");
            }

            char[] rowData = row.toCharArray();
            int length = rowData.length;

            for (int i = 0; i < length; ++i) {
                if (rowData[i] == ',' && !tdIsOpen) {
                    contentHTML.append("</td>\n");
                    contentHTML.append("    <td>");
                } else if (tdIsOpen && rowData[i] == '"' && i + 1 < length && rowData[i + 1] == '"') {
                    contentHTML.append(rowData[i]);
                    ++i;
                } else if (rowData[i] == '"') {
                    tdIsOpen = !tdIsOpen;
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

            if (!tdIsOpen) {
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
