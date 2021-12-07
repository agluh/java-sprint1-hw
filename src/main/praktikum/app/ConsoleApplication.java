package praktikum.app;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import praktikum.command.LoadMonthlyReportsCommand;
import praktikum.command.LoadYearlyReportCommand;
import praktikum.command.PrintMonthlyReportsCommand;
import praktikum.command.PrintYearlyReportCommand;
import praktikum.command.ValidateReportsCommand;
import praktikum.controller.Controller;
import praktikum.view.InvalidCommandView;
import praktikum.view.View;

public class ConsoleApplication implements Application {
    private final InputStream input;
    private final PrintStream output;
    private final Controller controller;

    enum MenuCommand {
        LOAD_MONTHLY_REPORTS("1", "Считать все месячные отчёты"),
        LOAD_YEARLY_REPORT("2", "Считать годовой отчёт"),
        VALIDATE_REPORTS("3", "Сверить отчёты"),
        PRINT_MONTHLY_REPORTS("4", "Вывести информацию о всех месячных отчётах"),
        PRINT_YEARLY_REPORT("5", "Вывести информацию о годовом отчёте"),
        EXIT("0", "Выйти из программы");

        String key;
        String name;

        MenuCommand(String key, String name) {
            this.key = key;
            this.name = name;
        }

        static MenuCommand fromString(String key) {
            for (MenuCommand v : MenuCommand.values()) {
                if (v.key.equals(key)) {
                    return v;
                }
            }

            throw new IllegalArgumentException(String.format("Unknown menu command key: %s", key));
        }

        @Override
        public String toString() {
            return key + " - " + name;
        }
    }

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

            MenuCommand command;
            try {
                command = MenuCommand.fromString(scanner.next());
            } catch (IllegalArgumentException e) {
                output.println(new InvalidCommandView().render());
                output.println();
                continue;
            }

            if (command == MenuCommand.EXIT) {
                output.println("До свидания!");
                break;
            }

            View view;
            switch (command) {
                case LOAD_MONTHLY_REPORTS:
                    view = controller.process(new LoadMonthlyReportsCommand());
                    break;
                case LOAD_YEARLY_REPORT:
                    view = controller.process(new LoadYearlyReportCommand());
                    break;
                case VALIDATE_REPORTS:
                    view = controller.process(new ValidateReportsCommand());
                    break;
                case PRINT_MONTHLY_REPORTS:
                    view = controller.process(new PrintMonthlyReportsCommand());
                    break;
                case PRINT_YEARLY_REPORT:
                    view = controller.process(new PrintYearlyReportCommand());
                    break;
                default:
                    view = new InvalidCommandView();
            }

            output.println(view.render());
            output.println();
        }
    }

    private void printMenu() {
        output.println("Пожалуйста, выберите действие:");
        for (MenuCommand command : MenuCommand.values()) {
            output.println(command);
        }
    }
}
