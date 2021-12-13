package praktikum.command;

import praktikum.app.InputInterface;
import praktikum.app.OutputInterface;
import praktikum.domain.AbsentOfYearlyReportException;
import praktikum.domain.AccountingHelper;
import praktikum.presentation.YearlyReportPresentation;
import praktikum.view.YearlyReportView;

public class PrintYearlyReportCommand implements Command {
    private static final String KEY = "5";
    private static final String DESCRIPTION = "Вывести информацию о годовом отчёте";
    private final AccountingHelper helper;

    public PrintYearlyReportCommand(AccountingHelper helper) {
        this.helper = helper;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public Status execute(InputInterface input, OutputInterface output) {
        try {
            YearlyReportPresentation presentation = helper.getYearlyReportPresentation();
            output.success(new YearlyReportView(presentation).toString());
        } catch (AbsentOfYearlyReportException e) {
            output.error("Годовой отчёт ещё не был загружен!");
        }

        return Status.CONTINUE;
    }
}
