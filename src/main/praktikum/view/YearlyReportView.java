package praktikum.view;

import praktikum.presentation.*;

public class YearlyReportView implements View {
    private final YearlyReportPresentation presentation;

    public YearlyReportView(YearlyReportPresentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public String render() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Отчёт за %d год%n", presentation.getYear()));
        builder.append("----------------------------------------------");
        builder.append(System.lineSeparator());
        presentation.getProfit()
                .forEach(e -> builder.append(String.format("Прибыль за %s составила: %d%n", e.getMonth(), e.getProfit())));
        builder.append(System.lineSeparator());
        builder.append(String.format("Средний расход за все месяцы: %.2f%n", presentation.getAvgExpense()));
        builder.append(String.format("Средний доход за все месяцы: %.2f%n", presentation.getAvgIncome()));

        return builder.toString();
    }
}
