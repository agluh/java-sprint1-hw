package praktikum.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.domain.Month;
import praktikum.domain.MonthlyReport;

class MonthlyReportPresentationTest {
    private List<MonthlyReport> list;

    private static final int YEAR = 2021;
    private static final Month MONTH = Month.JANUARY;
    private static final String[] PRODUCTS = {"Income 1", "Income 2", "Expense 1", "Expense 2"};
    private static final int[] QUANTITY = {1, 2, 5, 1};
    private static final int[] UNIT_PRICE = {100, 30, 10, 250};

    @BeforeEach
    void init() {
        list = new ArrayList<>();
        MonthlyReport mr1 = new MonthlyReport(YEAR, MONTH);
        mr1.addEntry(PRODUCTS[0], QUANTITY[0], UNIT_PRICE[0], false);
        mr1.addEntry(PRODUCTS[1], QUANTITY[1], UNIT_PRICE[1], false);
        mr1.addEntry(PRODUCTS[2], QUANTITY[2], UNIT_PRICE[2], true);
        mr1.addEntry(PRODUCTS[3], QUANTITY[3], UNIT_PRICE[3], true);
        list.add(mr1);
    }

    @AfterEach
    void teardown() {
        list.clear();
    }

    @Test
    void testConstructor() {
        MonthlyReportPresentation presentation = new MonthlyReportPresentation(list);

        MonthlyReportPresentation.Item item = presentation.getEntries().findFirst().orElseThrow();

        assertEquals(YEAR, item.getYear());
        assertEquals(MONTH, item.getMonth());

        int expectedLargestExpense = QUANTITY[3] * UNIT_PRICE[3];
        assertEquals(expectedLargestExpense, item.getLargestExpense().getAmount());
        assertEquals(PRODUCTS[3], item.getLargestExpense().getProductName());

        int expectedLargestIncome = QUANTITY[0] * UNIT_PRICE[0];
        assertEquals(expectedLargestIncome, item.getLargestIncome().getAmount());
        assertEquals(PRODUCTS[0], item.getLargestIncome().getProductName());
    }
}