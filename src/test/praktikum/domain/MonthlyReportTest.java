package praktikum.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyReportTest {
    private static final int YEAR = 2021;
    private static final Month MONTH = Month.JANUARY;
    private static final String PRODUCT = "Test product";
    private static final int QUANTITY = 5;
    private static final int UNIT_PRICE = 100;

    @Test
    public void testConstructor() {
        MonthlyReport report = new MonthlyReport(YEAR, MONTH);
        assertEquals(YEAR, report.getYear());
        assertEquals(MONTH, report.getMonth());
        assertEquals(0, report.getExpenses().count());
        assertEquals(0, report.getIncome().count());
    }

    @Test
    public void testInsertNewExpenseEntry() {
        MonthlyReport report = new MonthlyReport(YEAR, MONTH);
        report.addEntry(PRODUCT, QUANTITY, UNIT_PRICE, true);

        assertEquals(1, report.getExpenses().count());
        assertEquals(0, report.getIncome().count());

        MonthlyReport.Entry entry = report.getExpenses().findFirst().orElseThrow();

        assertEquals(PRODUCT, entry.getProductName());
        assertEquals(QUANTITY, entry.getQuantity());
        assertEquals(UNIT_PRICE, entry.getUnitPrice());
        assertTrue(entry.isExpense());
        assertEquals(YEAR, entry.getYear());
        assertEquals(MONTH, entry.getMonth());
    }

    @Test
    public void testInsertNewIncomeEntry() {
        MonthlyReport report = new MonthlyReport(YEAR, MONTH);
        report.addEntry(PRODUCT, QUANTITY, UNIT_PRICE, false);

        assertEquals(0, report.getExpenses().count());
        assertEquals(1, report.getIncome().count());

        MonthlyReport.Entry entry = report.getIncome().findFirst().orElseThrow();

        assertEquals(PRODUCT, entry.getProductName());
        assertEquals(QUANTITY, entry.getQuantity());
        assertEquals(UNIT_PRICE, entry.getUnitPrice());
        assertFalse(entry.isExpense());
        assertEquals(YEAR, entry.getYear());
        assertEquals(MONTH, entry.getMonth());
    }
}