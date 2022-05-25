package praktikum;

import praktikum.app.ConsoleApplication;
import praktikum.command.LoadMonthlyReportsCommand;
import praktikum.command.LoadYearlyReportCommand;
import praktikum.command.PrintMonthlyReportsCommand;
import praktikum.command.PrintYearlyReportCommand;
import praktikum.command.TerminateAppCommand;
import praktikum.command.ValidateReportsCommand;
import praktikum.domain.AccountingHelper;
import praktikum.domain.ReportParser;
import praktikum.io.CsvReportParser;
import praktikum.io.LocalReportRepository;

public class Main {
    public static void main(String[] args) {
        ReportParser parser = new CsvReportParser();
        AccountingHelper helper = new AccountingHelper(
                new LocalReportRepository(parser, "resources", "csv"),
                new LocalReportRepository(parser, "resources", "csv")
        );

        ConsoleApplication app = new ConsoleApplication(System.in, System.out);

        app.registerCommand(new LoadMonthlyReportsCommand(helper));
        app.registerCommand(new LoadYearlyReportCommand(helper));
        app.registerCommand(new ValidateReportsCommand(helper));
        app.registerCommand(new PrintMonthlyReportsCommand(helper));
        app.registerCommand(new PrintYearlyReportCommand(helper));
        app.registerCommand(new TerminateAppCommand());

        app.run();
    }
}

