package ru.vitalikeller.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название файла CSV:");
        String fileName = scanner.nextLine();

        System.out.println("Загрузка CSV...");
        StringBuilder csvContent = readCsvFile(fileName);

        System.out.println("Ковертация в HTML:" + fileName);
        StringBuilder htmlContent = convertCsvToHtml(csvContent);

    }

    private static StringBuilder readCsvFile(String fileName) {
        StringBuilder csvContent = new StringBuilder();
        try (Scanner scannerFile = new Scanner(new FileInputStream(fileName), "utf-8")) {
            while (scannerFile.hasNextLine()) {
                csvContent.append(scannerFile.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return csvContent;
    }

    private static StringBuilder convertCsvToHtml(StringBuilder contentCSV) {
        StringBuilder contentHTML = new StringBuilder();

        contentHTML.append("<html>\n");
        contentHTML.append("    <head> HTML </head>\n");
        contentHTML.append("    <body>\n");
        //contentHTML.append("        <table cols = \"5\" alignt = \"center\" border = \"1%\">\n");
        boolean tdIsOpen = false;

        for (String row : contentCSV.toString().split("\\n")) {
            if (!tdIsOpen) {
                contentHTML.append("        <tr align = \"center\">\n");
            }

            //tdIsOpen = makeCells(contentHTML, tdIsOpen, row);

            if (!tdIsOpen) {
                contentHTML.append("        </tr>\n");
            }
        }

        contentHTML.append("        </table>\n");
        contentHTML.append("    </body>\n");
        contentHTML.append("</html>\n");

        return contentHTML;
    }

}
