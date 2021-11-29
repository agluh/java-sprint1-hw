package praktikum.domain;

import java.util.List;

public class ReportsValidator {
    public static void validate(List<MonthlyReport> monthlyReports, YearlyReport yearlyReport) {
        for(MonthlyReport monthlyReport : monthlyReports) {
            int monthlyExpenses = monthlyReport.expenses()
                    .mapToInt(ReportsValidator::calcSubTotal)
                    .sum();
            int monthlyIncome = monthlyReport.income()
                    .mapToInt(ReportsValidator::calcSubTotal)
                    .sum();

            YearlyReport.Entry yearlyRecord = yearlyReport.recordAtMonth(monthlyReport.month());

            if(monthlyExpenses != yearlyRecord.expenses()
                || monthlyIncome != yearlyRecord.income()) {
                throw new ReportValidationException(monthlyReport.month());
            }
        }
    }

    private static int calcSubTotal(MonthlyReport.Entry e) {
        return e.quantity() * e.unitPrice();
    }
}
