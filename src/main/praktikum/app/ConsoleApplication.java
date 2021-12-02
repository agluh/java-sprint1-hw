package praktikum.app;

import praktikum.command.*;
import praktikum.controller.*;
import praktikum.view.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleApplication implements Application {
    private final InputStream input;
    private final PrintStream output;
    private final Controller controller;

    public ConsoleApplication(InputStream input, PrintStream output, Controller controller) {
        this.input = input;
        this.output = output;
        this.controller = controller;
    }

    @Override
    public void run() {
         Scanner scanner = new Scanner(input);

        while (true) {
            printMenu();

            int command;
            try {
                command = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                output.println(new InvalidCommandView().render());
                output.println();
                continue;
            }

            if(command == 0) {
                output.println("До свидания!");
                break;
            }

            View view;
            switch (command) {
                case 1: view = controller.process(new LoadMonthlyReportsCommand()); break;
                case 2: view = controller.process(new LoadYearlyReportCommand()); break;
                case 3: view = controller.process(new ValidateReportsCommand()); break;
                case 4: view = controller.process(new PrintMonthlyReportsCommand()); break;
                case 5: view = controller.process(new PrintYearlyReportCommand()); break;
                default: view = new InvalidCommandView();
            }

            output.println(view.render());
            output.println();
        }
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
