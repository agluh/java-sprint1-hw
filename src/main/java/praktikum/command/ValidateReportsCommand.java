package praktikum.command;

import praktikum.app.InputInterface;
import praktikum.app.OutputInterface;
import praktikum.domain.AbsentOfMonthlyReportsException;
import praktikum.domain.AbsentOfYearlyReportException;
import praktikum.domain.AccountingHelper;
import praktikum.domain.ReportValidationException;

public class ValidateReportsCommand implements Command {
    private static final String KEY = "3";
    private static final String DESCRIPTION = "Сверить отчёты";
    private final AccountingHelper helper;

    public ValidateReportsCommand(AccountingHelper helper) {
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
            helper.validateReports();
            output.success("Всё сходится, отчёты в порядке!");
        } catch (AbsentOfMonthlyReportsException e) {
            output.error("Месячные отчёты ещё не были загружены!");
        } catch (AbsentOfYearlyReportException e) {
            output.error("Годовой отчёт ещё не был загружен!");
        } catch (ReportValidationException e) {
            output.error(String.format("Данные в отчётах не сходятся. Проблемный месяц: %s%n",
                e.getMonthCausedAt()));
        }

        return Status.CONTINUE;
    }
}
