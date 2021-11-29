package praktikum.presentation;

import praktikum.domain.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YearlyReportPresentation {
    private final YearlyReport report;

    public YearlyReportPresentation(YearlyReport report) {
        this.report = report;
    }

    public Stream<Item> profit() {
        List<Item> profit = report.entries()
                .map(Item::new)
                .collect(Collectors.toList());
        return profit.stream();
    }

    public double avgIncome() {
        return report.entries()
                .mapToDouble(YearlyReport.Entry::income)
                .average()
                .orElse(0);
    }

    public double avgExpense() {
        return report.entries()
                .mapToDouble(YearlyReport.Entry::expenses)
                .average()
                .orElse(0);
    }

    public int year() {
        return report.year();
    }

    private static int calcSubTotal(YearlyReport.Entry entry) {
        return entry.income() - entry.expenses();
    }

    public static class Item {
        private final Month month;
        private final int profit;

        public Item(YearlyReport.Entry entry) {
            month = entry.month();
            profit = calcSubTotal(entry);
        }

        public Month month() {
            return month;
        }

        public int profit() {
            return profit;
        }
    }
}
