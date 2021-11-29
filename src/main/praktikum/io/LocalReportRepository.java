package praktikum.io;

import praktikum.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LocalReportRepository implements MonthlyReportRepository, YearlyReportRepository {
    private final ReportParser parser;
    private final String path;
    private final String extension;

    public LocalReportRepository(ReportParser parser, String path, String extension) {
        this.parser = parser;
        this.path = path;
        this.extension = extension;
    }

    @Override
    public MonthlyReport getByDate(int year, Month month) {
        String data = getFileData(String.format("m.%d%02d.%s", year, month.ordinal() + 1, extension));
        MonthlyReport report = new MonthlyReport(year, month);
        parser.populate(report, data);
        return report;
    }

    @Override
    public YearlyReport getByDate(int year) {
        String data = getFileData(String.format("y.%d.%s", year, extension));
        YearlyReport report = new YearlyReport(year);
        parser.populate(report, data);
        return report;
    }

    private String getFileData(String fileName) {
        try {
            return Files.readString(Path.of(path, fileName));
        } catch (IOException e) {
            throw new ReportLoadingException();
        }
    }
}
