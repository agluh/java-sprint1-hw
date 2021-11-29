package praktikum.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

public class YearlyReport {
    private final int year;
    private final Map<Month, Entry> entries;

    public YearlyReport(int year) {
        this.year = year;
        entries = new LinkedHashMap<>();
    }

    public int year() {
        return year;
    }

    public Stream<Entry> entries() {
        return entries.values().stream();
    }

    public Entry recordAtMonth(Month month) {
        if(!entries.containsKey(month)) {
            throw new NoSuchElementException("There is no records for month " + month + " in yearly report for " + year);
        }

        return entries.get(month);
    }

    public void addEntry(Month month, int income, int expense) {
        Entry entry = new Entry(month, income, expense);
        entries.put(month, entry);
    }

    public class Entry {
        private final Month month;
        private final int income;
        private final int expenses;

        private Entry(Month month, int income, int expenses) {
            Objects.requireNonNull(month);

            if(income < 0) {
                throw new IllegalArgumentException("Income value should be greater or equals to 0.");
            }

            if(expenses < 0) {
                throw new IllegalArgumentException("Expenses value should be greater or equals to 0.");
            }

            this.month = month;
            this.income = income;
            this.expenses = expenses;
        }

        public Month month() {
            return month;
        }

        public int year() {
            return YearlyReport.this.year();
        }

        public int income() {
            return income;
        }

        public int expenses() {
            return expenses;
        }
    }
}
