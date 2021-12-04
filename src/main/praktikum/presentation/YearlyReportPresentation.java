package praktikum.presentation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import praktikum.domain.Month;
import praktikum.domain.YearlyReport;

/*
 * This class is basically a presentation model and is used as
 * a thin adapter between the domain level and the view.
 */
public class YearlyReportPresentation {
    private final YearlyReport report;

    public YearlyReportPresentation(YearlyReport report) {
        this.report = report;
    }

    public Stream<Item> getProfit() {
        List<Item> profit = report.getEntries()
                .map(Item::new)
                .collect(Collectors.toList());
        return profit.stream();
    }

    public double getAvgIncome() {
        return report.getEntries()
                .mapToDouble(YearlyReport.Entry::getIncome)
                .average()
                .orElse(0);
    }

    public double getAvgExpense() {
        return report.getEntries()
                .mapToDouble(YearlyReport.Entry::getExpenses)
                .average()
                .orElse(0);
    }

    public int getYear() {
        return report.getYear();
    }

    private static int calcSubTotal(YearlyReport.Entry entry) {
        return entry.getIncome() - entry.getExpenses();
    }

    public static class Item {
        private final Month month;
        private final int profit;

        public Item(YearlyReport.Entry entry) {
            month = entry.getMonth();
            profit = calcSubTotal(entry);
        }

        public Month getMonth() {
            return month;
        }

        public int getProfit() {
            return profit;
        }
    }
}
