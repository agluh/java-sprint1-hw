package praktikum;

import praktikum.domain.*;
import praktikum.io.*;
import praktikum.app.*;

public class Main {

    public static void main(String[] args) {
        ReportParser parser = new CSVReportParser();
        AccountingHelper helper = new AccountingHelper(
                new LocalReportRepository(parser, "resources", "csv"),
                new LocalReportRepository(parser, "resources", "csv")
        );
        Application app = new ConsoleApplication(System.in, System.out, helper);
        app.run();
    }
}

