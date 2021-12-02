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

        assertEquals(YEAR, report.getYear());
        assertEquals(0, report.getEntries().count());
    }

    @Test
    public void testInsertNewEntry() {
        YearlyReport report = new YearlyReport(YEAR);

        report.addEntry(MONTH, INCOME, EXPENSE);

        YearlyReport.Entry entry = report.getEntries().findFirst().orElseThrow();

        assertEquals(YEAR, entry.getYear());
        assertEquals(MONTH, entry.getMonth());
        assertEquals(INCOME, entry.getIncome());
        assertEquals(EXPENSE, entry.getExpenses());
    }

    @Test
    public void testGetEntryByMonth() {
        YearlyReport report = new YearlyReport(YEAR);

        report.addEntry(MONTH, INCOME, EXPENSE);

        YearlyReport.Entry entry = report.getRecordAtMonth(MONTH);

        assertEquals(YEAR, entry.getYear());
        assertEquals(MONTH, entry.getMonth());
        assertEquals(INCOME, entry.getIncome());
        assertEquals(EXPENSE, entry.getExpenses());
    }

    @Test
    public void testGetEntryForNotExistedMonth() {
        YearlyReport report = new YearlyReport(YEAR);

        report.addEntry(MONTH, INCOME, EXPENSE);

        assertThrows(NoSuchElementException.class, () ->
                report.getRecordAtMonth(NOT_EXISTED_MONTH));
    }
}