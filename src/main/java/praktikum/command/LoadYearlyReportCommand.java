package praktikum.command;

import praktikum.app.InputInterface;
import praktikum.app.OutputInterface;
import praktikum.domain.AccountingHelper;
import praktikum.io.ReportLoadingException;
import praktikum.io.ReportParsingException;

public class LoadYearlyReportCommand implements Command {
    private static final String KEY = "2";
    private static final String DESCRIPTION = "Считать годовой отчёт";
    private final AccountingHelper helper;

    public LoadYearlyReportCommand(AccountingHelper helper) {
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
            helper.loadYearlyReport();
            output.success("Годовой отчёт успешно загружен!");
        } catch (ReportLoadingException e) {
            output.error("Не удалось загрузить отчёт!");
        } catch (ReportParsingException e) {
            output.error("Не удалось разобрать отчёт!");
        }

        return Status.CONTINUE;
    }
}
