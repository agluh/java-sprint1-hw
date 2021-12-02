package praktikum.domain;

import java.util.List;

public class ReportsValidator {
    public static void validate(List<MonthlyReport> monthlyReports, YearlyReport yearlyReport) {
        for (MonthlyReport monthlyReport : monthlyReports) {
            int monthlyExpenses = monthlyReport.getExpenses()
                    .mapToInt(ReportsValidator::calcSubTotal)
                    .sum();
            int monthlyIncome = monthlyReport.getIncome()
                    .mapToInt(ReportsValidator::calcSubTotal)
                    .sum();

            YearlyReport.Entry yearlyRecord =
                    yearlyReport.getRecordAtMonth(monthlyReport.getMonth());

            if (monthlyExpenses != yearlyRecord.getExpenses()
                    || monthlyIncome != yearlyRecord.getIncome()) {
                throw new ReportValidationException(monthlyReport.getMonth());
            }
        }
    }

    private static int calcSubTotal(MonthlyReport.Entry e) {
        return e.getQuantity() * e.getUnitPrice();
    }
}
