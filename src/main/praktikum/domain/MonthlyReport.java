package praktikum.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MonthlyReport {
    private final int year;
    private final Month month;
    private final List<Entry> entries;

    public MonthlyReport(int year, Month month) {
        this.year = year;
        this.month = month;
        entries = new ArrayList<>();
    }

    public int year() {
        return year;
    }

    public Month month() {
        return month;
    }

    public Stream<Entry> income() {
        return entries.stream().filter(Predicate.not(Entry::isExpense));
    }

    public Stream<Entry> expenses() {
        return entries.stream().filter(Entry::isExpense);
    }

    public void addEntry(String productName, int quantity, int unitPrice, boolean isExpense) {
        Entry entry = this.new Entry(productName, quantity, unitPrice, isExpense);
        entries.add(entry);
    }

    public class Entry {
        private final String productName;
        private final int quantity;
        private final int unitPrice;
        private final boolean isExpense;

        private Entry(String productName, int quantity, int unitPrice, boolean isExpense) {
            Objects.requireNonNull(productName);

            if (productName.isEmpty()) {
                throw new IllegalArgumentException("Product name should not be empty");
            }

            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity value should be greater than 0.");
            }

            if (unitPrice <= 0) {
                throw new IllegalArgumentException("Unit price value should be greater than 0.");
            }

            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.isExpense = isExpense;
        }

        public String productName() {
            return productName;
        }

        public int quantity() {
            return quantity;
        }

        public int unitPrice() {
            return unitPrice;
        }

        public boolean isExpense() {
            return isExpense;
        }

        public int year() {
            return MonthlyReport.this.year();
        }

        public Month month() {
            return MonthlyReport.this.month();
        }
    }
}
