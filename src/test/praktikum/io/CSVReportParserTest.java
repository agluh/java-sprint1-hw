package praktikum.io;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import praktikum.domain.Month;
import praktikum.domain.MonthlyReport;
import praktikum.domain.YearlyReport;

class CSVReportParserTest {
    private final String CORRECT_MONTHLY_DATA = "item_name,is_expense,quantity,sum_of_one\n" +
            "Заточка коньков,TRUE,50,200\n" +
            "Разморозка ледопарка,FALSE,1,20000";
    private final String CORRECT_YEARLY_DATA = "month,amount,is_expense\n" +
            "01,1593150,false\n" +
            "01,350000,true";

    @Test
    public void testMonthlyReportParsing() {
        CSVReportParser parser = new CSVReportParser();
        MonthlyReport report = Mockito.mock(MonthlyReport.class);
        parser.populate(report, CORRECT_MONTHLY_DATA);
        Mockito.verify(report, Mockito.times(1)).addEntry("Заточка коньков", 50, 200, true);
        Mockito.verify(report, Mockito.times(1)).addEntry("Разморозка ледопарка", 1, 20000, false);
    }

    @Test
    public void testYearlyReportParsing() {
        CSVReportParser parser = new CSVReportParser();
        YearlyReport report = Mockito.mock(YearlyReport.class);
        parser.populate(report, CORRECT_YEARLY_DATA);
        Mockito.verify(report, Mockito.times(1)).addEntry(Month.JANUARY, 1593150, 350000);
    }
}