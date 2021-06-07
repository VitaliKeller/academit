package ru.vitalikeller.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите название файла CSV: ");
        //String fileName = scanner.nextLine();
        String fileName = "text.csv";
        System.out.println(fileName);

        System.out.println("... Загрузка файла.");
        StringBuilder csvContent = readCsvFile(fileName);

        System.out.println("... Конвертация в HTML: " + fileName);
        StringBuilder htmlContent = convertCsvToHtml(csvContent);

        System.out.println("... вывод в файл ");
        String outFileName = "index.html";
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
        contentHTML.append("        <table>\n");

        // boolean trIsOpen = false;
        boolean tdIsOpen = true;

        for (String row : contentCSV.toString().split("\\n")) {
            // открыть строку, если ячейка не открыта
            if (!tdIsOpen) {
                contentHTML.append("          <tr>\n");
            }

            // тут обработка строки

            // закрыть строку, если ячейка не открыта
            if (!tdIsOpen) {
                contentHTML.append("          </tr>\n");
            }
        }

        contentHTML.append("        </table>\n");
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
