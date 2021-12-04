package praktikum.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.domain.Month;
import praktikum.domain.YearlyReport;

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
        assertEquals(expectedAvgExpense, presentation.getAvgExpense());

        double expectedAvgIncome = (double) (INCOME[0] + INCOME[1]) / 2;
        assertEquals(expectedAvgIncome, presentation.getAvgIncome());

        assertEquals(YEAR, presentation.getYear());

        var ref = new Object() {
            int index = 0;
        };

        presentation.getProfit().forEach(a -> {
            assertEquals(MONTHS[ref.index], a.getMonth());
            int expectedProfit = INCOME[ref.index] - EXPENSES[ref.index];
            assertEquals(expectedProfit, a.getProfit());
            ref.index++;
        });
    }
}