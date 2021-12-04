package praktikum.domain;

import java.util.ArrayList;
import praktikum.presentation.MonthlyReportPresentation;
import praktikum.presentation.YearlyReportPresentation;

/**
 * This class is used as a domain service for orchestration purposes.
 */
public class AccountingHelper {
    private final MonthlyReportRepository monthlyReportRepository;
    private final YearlyReportRepository yearlyReportRepository;
    private final ArrayList<MonthlyReport> monthlyReports;
    private YearlyReport yearlyReport;

    public AccountingHelper(MonthlyReportRepository monthlyReportRepository,
                            YearlyReportRepository yearlyReportRepository) {
        this.monthlyReportRepository = monthlyReportRepository;
        this.yearlyReportRepository = yearlyReportRepository;
        monthlyReports = new ArrayList<>();
    }

    public void loadMonthlyReports() {
        // In case user loaded reports more than once.
        // Maybe better use java.util.Set instead...
        monthlyReports.clear();

        monthlyReports.add(monthlyReportRepository.getByDate(2021, Month.JANUARY));
        monthlyReports.add(monthlyReportRepository.getByDate(2021, Month.FEBRUARY));
        monthlyReports.add(monthlyReportRepository.getByDate(2021, Month.MARCH));
    }

    public void loadYearlyReport() {
        yearlyReport = yearlyReportRepository.getByDate(2021);
    }

    public void validateReports() {
        ensureMonthlyReportsAreLoaded();
        ensureYearlyReportIsLoaded();

        ReportsValidator.validate(monthlyReports, yearlyReport);
    }

    public MonthlyReportPresentation getMonthlyReportsPresentation() {
        ensureMonthlyReportsAreLoaded();

        return new MonthlyReportPresentation(monthlyReports);
    }

    public YearlyReportPresentation getYearlyReportPresentation() {
        ensureYearlyReportIsLoaded();

        return new YearlyReportPresentation(yearlyReport);
    }

    private void ensureMonthlyReportsAreLoaded() {
        if (monthlyReports.isEmpty()) {
            throw new AbsentOfMonthlyReportsException();
        }
    }

    private void ensureYearlyReportIsLoaded() {
        if (yearlyReport == null) {
            throw new AbsentOfYearlyReportException();
        }
    }
}
