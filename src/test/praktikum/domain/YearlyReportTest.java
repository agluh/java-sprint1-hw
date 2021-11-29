package praktikum.domain;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class YearlyReportTest {
    private static final int YEAR = 2021;
    private static final Month MONTH = Month.JANUARY;
    private static final Month NOT_EXISTED_MONTH = Month.FEBRUARY;
    private static final int INCOME = 5;
    private static final int EXPENSE = 100;

    @Test
    public void testConstructor() {
        YearlyReport report = new YearlyReport(YEAR);

        assertEquals(YEAR, report.year());
        assertEquals(0, report.entries().count());
    }

    @Test
    public void testInsertNewEntry() {
        YearlyReport report = new YearlyReport(YEAR);

        report.addEntry(MONTH, INCOME, EXPENSE);

        YearlyReport.Entry entry = report.entries().findFirst().orElseThrow();

        assertEquals(YEAR, entry.year());
        assertEquals(MONTH, entry.month());
        assertEquals(INCOME, entry.income());
        assertEquals(EXPENSE, entry.expenses());
    }

    @Test
    public void testGetEntryByMonth() {
        YearlyReport report = new YearlyReport(YEAR);

        report.addEntry(MONTH, INCOME, EXPENSE);

        YearlyReport.Entry entry = report.recordAtMonth(MONTH);

        assertEquals(YEAR, entry.year());
        assertEquals(MONTH, entry.month());
        assertEquals(INCOME, entry.income());
        assertEquals(EXPENSE, entry.expenses());
    }

    @Test
    public void testGetEntryForNotExistedMonth() {
        YearlyReport report = new YearlyReport(YEAR);

        report.addEntry(MONTH, INCOME, EXPENSE);

        assertThrows(NoSuchElementException.class, () -> report.recordAtMonth(NOT_EXISTED_MONTH));
    }
}