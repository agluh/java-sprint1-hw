package praktikum.presentation;

import praktikum.domain.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/*
 * This class is basically a presentation model and is used as
 * a thin adapter between the domain level and the view.
 */
public class MonthlyReportPresentation {
    private final List<MonthlyReport> reports;

    public MonthlyReportPresentation(List<MonthlyReport> reports) {
        this.reports = reports;
    }

    public Stream<Item> getEntries() {
        return reports.stream().map(e -> {
            Product mostPopular = e.getIncome()
                    .max(Comparator.comparingInt(MonthlyReportPresentation::calcSubTotal))
                    .map(Product::new)
                    .orElse(Product.UNKNOWN);

            Product mostExpense = e.getExpenses()
                    .max(Comparator.comparingInt(MonthlyReportPresentation::calcSubTotal))
                    .map(Product::new)
                    .orElse(Product.UNKNOWN);

            return new Item(e.getYear(), e.getMonth(), mostPopular, mostExpense);
        });
    }

    private static int calcSubTotal(MonthlyReport.Entry entry) {
        return entry.getQuantity() * entry.getUnitPrice();
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

        public int getYear() {
            return year;
        }

        public Month getMonth() {
            return month;
        }

        public Product getLargestIncome() {
            return largestIncome;
        }

        public Product getLargestExpense() {
            return largestExpense;
        }
    }

    public static class Product {
        private final String productName;
        private final int amount;

        private static final Product UNKNOWN = new Product("Отсутствует", 0);

        public Product(MonthlyReport.Entry entry) {
            productName = entry.getProductName();
            amount = calcSubTotal(entry);
        }

        public Product(String productName, int amount) {
            this.productName = productName;
            this.amount = amount;
        }

        public String getProductName() {
            return productName;
        }

        public int getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return productName + ", " + amount;
        }
    }
}
