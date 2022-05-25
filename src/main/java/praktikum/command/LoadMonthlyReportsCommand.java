package praktikum.command;

import praktikum.app.InputInterface;
import praktikum.app.OutputInterface;
import praktikum.domain.AccountingHelper;
import praktikum.io.ReportLoadingException;
import praktikum.io.ReportParsingException;

public class LoadMonthlyReportsCommand implements Command {
    private static final String KEY = "1";
    private static final String DESCRIPTION = "Считать все месячные отчёты";
    private final AccountingHelper helper;

    public LoadMonthlyReportsCommand(AccountingHelper helper) {
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
            helper.loadMonthlyReports();
            output.success("Месячные отчёты успешно загружены!");
        } catch (ReportLoadingException e) {
            output.error("Не удалось загрузить отчёт!");
        } catch (ReportParsingException e) {
            output.error("Не удалось разобрать отчёт!");
        }

        return Status.CONTINUE;
    }
}
