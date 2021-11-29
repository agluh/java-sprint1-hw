package praktikum.presentation;

import praktikum.domain.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class MonthlyReportPresentation {
    private final List<MonthlyReport> reports;

    public MonthlyReportPresentation(List<MonthlyReport> reports) {
        this.reports = reports;
    }

    public Stream<Item> entries() {
        return reports.stream().map(e -> {
            Product mostPopular = e.income()
                    .max(Comparator.comparingInt(MonthlyReportPresentation::calcSubTotal))
                    .map(Product::new)
                    .orElse(Product.UNKNOWN);

            Product mostExpense = e.expenses()
                    .max(Comparator.comparingInt(MonthlyReportPresentation::calcSubTotal))
                    .map(Product::new)
                    .orElse(Product.UNKNOWN);

            return new Item(e.year(), e.month(), mostPopular, mostExpense);
        });
    }

    private static int calcSubTotal(MonthlyReport.Entry entry) {
        return entry.quantity() * entry.unitPrice();
    }

    public static class Item {
        private final int year;
        private final Month month;
        private final Product largestIncome;
        private final Product largestExpense;

        public Item(int year, Month month, Product largestIncome, Product largestExpense) {
            this.year = year;
            this.month = month;
            this.largestIncome = largestIncome;
            this.largestExpense = largestExpense;
        }

        public int year() {
            return year;
        }

        public Month month() {
            return month;
        }

        public Product largestIncome() {
            return largestIncome;
        }

        public Product largestExpense() {
            return largestExpense;
        }
    }

    public static class Product {
        private final String productName;
        private final int amount;

        private static final Product UNKNOWN = new Product("Отсутствует", 0);

        public Product(MonthlyReport.Entry entry) {
            productName = entry.productName();
            amount = calcSubTotal(entry);
        }

        public Product(String productName, int amount) {
            this.productName = productName;
            this.amount = amount;
        }

        public String productName() {
            return productName;
        }

        public int amount() {
            return amount;
        }

        @Override
        public String toString() {
            return productName + ", " + amount;
        }
    }
}
