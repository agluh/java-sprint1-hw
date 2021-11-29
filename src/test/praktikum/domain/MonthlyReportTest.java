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
        assertEquals(YEAR, report.year());
        assertEquals(MONTH, report.month());
        assertEquals(0, report.expenses().count());
        assertEquals(0, report.income().count());
    }

    @Test
    public void testInsertNewExpenseEntry() {
        MonthlyReport report = new MonthlyReport(YEAR, MONTH);
        report.addEntry(PRODUCT, QUANTITY, UNIT_PRICE, true);

        assertEquals(1, report.expenses().count());
        assertEquals(0, report.income().count());

        MonthlyReport.Entry entry = report.expenses().findFirst().orElseThrow();

        assertEquals(PRODUCT, entry.productName());
        assertEquals(QUANTITY, entry.quantity());
        assertEquals(UNIT_PRICE, entry.unitPrice());
        assertTrue(entry.isExpense());
        assertEquals(YEAR, entry.year());
        assertEquals(MONTH, entry.month());
    }

    @Test
    public void testInsertNewIncomeEntry() {
        MonthlyReport report = new MonthlyReport(YEAR, MONTH);
        report.addEntry(PRODUCT, QUANTITY, UNIT_PRICE, false);

        assertEquals(0, report.expenses().count());
        assertEquals(1, report.income().count());

        MonthlyReport.Entry entry = report.income().findFirst().orElseThrow();

        assertEquals(PRODUCT, entry.productName());
        assertEquals(QUANTITY, entry.quantity());
        assertEquals(UNIT_PRICE, entry.unitPrice());
        assertFalse(entry.isExpense());
        assertEquals(YEAR, entry.year());
        assertEquals(MONTH, entry.month());
    }
}