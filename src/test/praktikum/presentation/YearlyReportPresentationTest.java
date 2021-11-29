package praktikum.presentation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.domain.Month;
import praktikum.domain.YearlyReport;

import static org.junit.jupiter.api.Assertions.*;

class YearlyReportPresentationTest {
    private static final int YEAR = 2021;
    private static final Month[] MONTHS = {Month.JANUARY, Month.FEBRUARY};
    private static final int[] INCOME = {100, 200};
    private static final int[] EXPENSES = {50, 100};

    private YearlyReport report;

    @BeforeEach
    void init() {
        report = new YearlyReport(YEAR);
        report.addEntry(MONTHS[0], INCOME[0], EXPENSES[0]);
        report.addEntry(MONTHS[1], INCOME[1], EXPENSES[1]);
    }

    @AfterEach
    void teardown() {
        report = null;
    }

    @Test
    public void testConstructor() {
        YearlyReportPresentation presentation = new YearlyReportPresentation(report);

        double expectedAvgExpense = (double) (EXPENSES[0] + EXPENSES[1]) / 2;
        assertEquals(expectedAvgExpense, presentation.avgExpense());

        double expectedAvgIncome = (double) (INCOME[0] + INCOME[1]) / 2;
        assertEquals(expectedAvgIncome, presentation.avgIncome());

        assertEquals(YEAR, presentation.year());

        var ref = new Object() {
            int i = 0;
        };

        presentation.profit().forEach(a -> {
            assertEquals(MONTHS[ref.i], a.month());
            int expectedProfit = INCOME[ref.i] - EXPENSES[ref.i];
            assertEquals(expectedProfit, a.profit());
            ref.i++;
        });
    }
}