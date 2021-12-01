package praktikum.app;

import praktikum.domain.*;
import praktikum.io.*;
import praktikum.presentation.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleApplication implements Application {
    private final InputStream input;
    private final PrintStream output;
    private final AccountingHelper helper;

    private final static String BAD_CHOICE = "Извините, такой команды нет.";

    public ConsoleApplication(InputStream input, PrintStream output, AccountingHelper helper) {
        this.input = input;
        this.output = output;
        this.helper = helper;
    }

    @Override
    public void run() {
        boolean shouldExit = false;
        Scanner scanner = new Scanner(input);

        do {
            printMenu();

            int command;
            try {
                command = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                output.println(BAD_CHOICE);
                output.println();
                continue;
            }

            try {
                switch (command) {
                    case 0:
                        output.println("До свидания!");
                        shouldExit = true;
                        break;
                    case 1:
                        helper.loadMonthlyReports();
                        output.println("Месячные отчёты успешно загружены!");
                        break;
                    case 2:
                        helper.loadYearlyReport();
                        output.println("Годовой отчёт успешно загружен!");
                        break;
                    case 3:
                        helper.validateReports();
                        output.println("Всё сходится, отчёты в порядке!");
                        break;
                    case 4:
                        printMonthlyReports();
                        break;
                    case 5:
                        printYearlyReport();
                        break;
                    default:
                        output.println(BAD_CHOICE);
                }
            } catch (AbsentOfMonthlyReportsException e) {
                output.println("Ошибка: месячные отчёты ещё не были загружены!");
            } catch (AbsentOfYearlyReportException e) {
                output.println("Ошибка: годовой отчёт ещё не был загружен!");
            } catch (ReportLoadingException e) {
                output.println("Ошибка: не удалось загрузить отчёт!");
            } catch (ReportParsingException e) {
                output.println("Ошибка: не удалось разобрать отчёт!");
            } catch (ReportValidationException e) {
                output.printf("Ошибка: данные в отчётах не сходятся. Проблемный месяц: %s%n", e.monthCausedAt());
            }

            output.println();
        } while (!shouldExit);
    }

    private void printMonthlyReports() {
        MonthlyReportPresentation monthlyReportPresentation = helper.getMonthlyReportsPresentation();
        monthlyReportPresentation.getEntries()
                .forEach(e -> {
                    output.printf("Отчёт за %s %d года%n", e.getMonth(), e.getYear());
                    output.println("----------------------------------------------");
                    output.printf("Самый прибыльный товар: %s%n", e.getLargestIncome());
                    output.printf("Самая большая трата: %s%n", e.getLargestExpense());
                    output.println();
                });
    }

    private void printYearlyReport() {
        YearlyReportPresentation yearlyReportPresentation = helper.getYearlyReportPresentation();
        output.printf("Отчёт за %d год%n", yearlyReportPresentation.getYear());
        output.println("----------------------------------------------");
        yearlyReportPresentation.getProfit()
                .forEach(e -> output.printf("Прибыль за %s составила: %d%n", e.getMonth(), e.getProfit()));
        output.println();
        output.printf("Средний расход за все месяцы: %.2f%n", yearlyReportPresentation.getAvgExpense());
        output.printf("Средний доход за все месяцы: %.2f%n", yearlyReportPresentation.getAvgIncome());
    }

    private void printMenu() {
        output.println("Пожалуйста, выберите действие:");
        output.println("1 - Считать все месячные отчёты");
        output.println("2 - Считать годовой отчёт");
        output.println("3 - Сверить отчёты");
        output.println("4 - Вывести информацию о всех месячных отчётах");
        output.println("5 - Вывести информацию о годовом отчёте");
        output.println("Чтобы выйти из программы, наберите 0");
    }
}
