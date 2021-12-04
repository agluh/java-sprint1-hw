package praktikum;

import praktikum.app.Application;
import praktikum.app.ConsoleApplication;
import praktikum.controller.AccountingController;
import praktikum.controller.Controller;
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
        Controller controller = new AccountingController(helper);
        Application app = new ConsoleApplication(System.in, System.out, controller);
        app.run();
    }
}

