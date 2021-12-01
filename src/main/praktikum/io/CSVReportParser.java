package praktikum.io;

import praktikum.domain.*;

public class CSVReportParser implements ReportParser {

    /**
     * Each line of yearly CSV report has the following structure:
     * month,amount,is_expense
     *
     * The first line should be a header, and it will be ignored during
     * parsing process. Empty file (without entries) should still have a header.
     *
     * There should be a pair of entries for each month:
     * one with is_expense as true and one with is_expense as false.
     * Order of those two entries is not determinate.
     *
     * @throws ReportParsingException in case of errors
     */
    @Override
    public void populate(YearlyReport report, String data) {
        String[] lines = data.split("\\R");
        if (lines.length % 2 == 0) {
            throw new ReportParsingException();
        }

        for (int i = 1; i < lines.length; i += 2) {
            String[] firstLine = lines[i].split(",");
            String[] secondLine = lines[i + 1].split(",");

            if (firstLine.length != 3 || secondLine.length != 3) {
                throw new ReportParsingException();
            }

            try {
                int month = Integer.parseInt(firstLine[0]);
                int firstAmount = Integer.parseInt(firstLine[1]);
                boolean firstIsExpense = Boolean.parseBoolean(firstLine[2]);

                int secondMonth = Integer.parseInt(secondLine[0]);
                int secondAmount = Integer.parseInt(secondLine[1]);
                boolean secondIsExpense = Boolean.parseBoolean(secondLine[2]);

                if (month != secondMonth) {
                    throw new ReportParsingException();
                }

                if (firstIsExpense && secondIsExpense || !firstIsExpense && !secondIsExpense) {
                    throw new ReportParsingException();
                }

                int income = !firstIsExpense ? firstAmount : secondAmount;
                int expenses = firstIsExpense ? firstAmount : secondAmount;

                report.addEntry(Month.fromInteger(month), income, expenses);
            } catch (IllegalArgumentException e) {
                throw new ReportParsingException(e);
            }
        }
    }

    /**
     * Each line of monthly CSV report has the following structure:
     * item_name,is_expense,quantity,sum_of_one
     *
     * The first line should be a header, and it will be ignored during
     * parsing process. Empty file (without entries) should still have a header.
     *
     * @throws ReportParsingException in case of errors
     */
    @Override
    public void populate(MonthlyReport report, String data) {
        String[] lines = data.split("\\R");
        if (lines.length == 0) {
            throw new ReportParsingException();
        }

        for (int i = 1; i < lines.length; i++) {
            String[] line = lines[i].split(",");

            try {
                String productName = line[0];
                boolean isExpense = Boolean.parseBoolean(line[1]);
                int quantity = Integer.parseInt(line[2]);
                int unitPrice = Integer.parseInt(line[3]);

                report.addEntry(productName, quantity, unitPrice, isExpense);
            } catch (IllegalArgumentException e) {
                throw new ReportParsingException(e);
            }
        }
    }
}
