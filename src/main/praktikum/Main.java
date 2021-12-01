package praktikum;

import praktikum.domain.*;
import praktikum.io.*;
import praktikum.presentation.*;

import java.util.Scanner;

public class Main {
    private final static String BAD_CHOICE = "Извините, такой команды нет.";

    public static void main(String[] args) {
        boolean shouldExit = false;
        Scanner scanner = new Scanner(System.in);
        ReportParser parser = new CSVReportParser();
        AccountingHelper helper = new AccountingHelper(
                new LocalReportRepository(parser, "resources", "csv"),
                new LocalReportRepository(parser, "resources", "csv")
        );

        do {
            printMenu();

            int command;
            try {
                command = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println(BAD_CHOICE);
                System.out.println();
                continue;
            }

            try {
                switch (command) {
                    case 0:
                        System.out.println("До свидания!");
                        shouldExit = true;
                        break;
                    case 1:
                        helper.loadMonthlyReports();
                        System.out.println("Месячные отчёты успешно загружены!");
                        break;
                    case 2:
                        helper.loadYearlyReport();
                        System.out.println("Годовой отчёт успешно загружен!");
                        break;
                    case 3:
                        helper.validateReports();
                        System.out.println("Всё сходится, отчёты в порядке!");
                        break;
                    case 4:
                        MonthlyReportPresentation monthlyReportPresentation = helper.getMonthlyReportsPresentation();
                        monthlyReportPresentation.entries()
                                .forEach(e -> {
                                    System.out.printf("Отчёт за %s %d года%n", e.month(), e.year());
                                    System.out.println("----------------------------------------------");
                                    System.out.printf("Самый прибыльный товар: %s%n", e.largestIncome());
                                    System.out.printf("Самая большая трата: %s%n", e.largestExpense());
                                    System.out.println();
                                });
                        break;
                    case 5:
                        YearlyReportPresentation yearlyReportPresentation = helper.getYearlyReportPresentation();
                        System.out.printf("Отчёт за %d год%n", yearlyReportPresentation.year());
                        System.out.println("----------------------------------------------");
                        yearlyReportPresentation.profit()
                                .forEach(e -> System.out.printf("Прибыль за %s составила: %d%n", e.month(), e.profit()));
                        System.out.println();
                        System.out.printf("Средний расход за все месяцы: %.2f%n", yearlyReportPresentation.avgExpense());
                        System.out.printf("Средний доход за все месяцы: %.2f%n", yearlyReportPresentation.avgIncome());
                        break;
                    default:
                        System.out.println(BAD_CHOICE);
                }
            } catch (AbsentOfMonthlyReportsException e) {
                System.out.println("Ошибка: месячные отчёты ещё не были загружены!");
            } catch (AbsentOfYearlyReportException e) {
                System.out.println("Ошибка: годовой отчёт ещё не был загружен!");
            } catch (ReportLoadingException e) {
                System.out.println("Ошибка: не удалось загрузить отчёт!");
            } catch (ReportParsingException e) {
                System.out.println("Ошибка: не удалось разобрать отчёт!");
            } catch (ReportValidationException e) {
                System.out.printf("Ошибка: данные в отчётах не сходятся. Проблемный месяц: %s%n", e.monthCausedAt());
            }

            System.out.println();
        } while (!shouldExit);
    }

    private static void printMenu() {
        System.out.println("Пожалуйста, выберите действие:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("Чтобы выйти из программы, наберите 0");
    }
}

