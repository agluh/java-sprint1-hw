package praktikum.command;

import praktikum.app.InputInterface;
import praktikum.app.OutputInterface;
import praktikum.domain.AbsentOfMonthlyReportsException;
import praktikum.domain.AccountingHelper;
import praktikum.presentation.MonthlyReportPresentation;
import praktikum.view.MonthlyReportsView;

public class PrintMonthlyReportsCommand implements Command {
    private static final String KEY = "4";
    private static final String DESCRIPTION = "Вывести информацию о всех месячных отчётах";
    private final AccountingHelper helper;

    public PrintMonthlyReportsCommand(AccountingHelper helper) {
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
            MonthlyReportPresentation presentation = helper.getMonthlyReportsPresentation();
            output.success(new MonthlyReportsView(presentation).toString());
        } catch (AbsentOfMonthlyReportsException e) {
            output.error("Месячные отчёты ещё не были загружены!");
        }

        return Status.CONTINUE;
    }
}
