package praktikum.view;

import praktikum.presentation.MonthlyReportPresentation;

public class MonthlyReportsView implements View {
    private final MonthlyReportPresentation presentation;

    public MonthlyReportsView(MonthlyReportPresentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public String render() {
        StringBuilder builder = new StringBuilder();

        presentation.getEntries()
                .forEach(e -> {
                    builder.append(String.format("Отчёт за %s %d года%n",
                            e.getMonth(), e.getYear()));
                    builder.append("----------------------------------------------");
                    builder.append(System.lineSeparator());
                    builder.append(String.format("Самый прибыльный товар: %s%n",
                            e.getLargestIncome()));
                    builder.append(String.format("Самая большая трата: %s%n",
                            e.getLargestExpense()));
                    builder.append(System.lineSeparator());
                });

        return builder.toString();
    }
}
