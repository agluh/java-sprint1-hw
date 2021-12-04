package praktikum.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import praktikum.command.Command;
import praktikum.command.LoadMonthlyReportsCommand;
import praktikum.command.LoadYearlyReportCommand;
import praktikum.command.PrintMonthlyReportsCommand;
import praktikum.command.PrintYearlyReportCommand;
import praktikum.command.ValidateReportsCommand;
import praktikum.domain.AbsentOfMonthlyReportsException;
import praktikum.domain.AbsentOfYearlyReportException;
import praktikum.domain.AccountingHelper;
import praktikum.domain.ReportValidationException;
import praktikum.io.ReportLoadingException;
import praktikum.io.ReportParsingException;
import praktikum.presentation.MonthlyReportPresentation;
import praktikum.presentation.YearlyReportPresentation;
import praktikum.view.ErrorView;
import praktikum.view.MonthlyReportsView;
import praktikum.view.SuccessView;
import praktikum.view.View;
import praktikum.view.YearlyReportView;

public class AccountingController implements Controller {
    private final AccountingHelper helper;

    public AccountingController(AccountingHelper helper) {
        this.helper = helper;
    }

    @Override
    public View process(Command command) {
        try {
            Class<?> aClass = this.getClass();
            String methodName = getHandlerNameForCommand(command);
            Method method = aClass.getDeclaredMethod(methodName, command.getClass());
            return (View) method.invoke(this, command);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            try {
                throw ex.getCause();
            } catch (AbsentOfMonthlyReportsException e) {
                return new ErrorView("Месячные отчёты ещё не были загружены!");
            } catch (AbsentOfYearlyReportException e) {
                return new ErrorView("Годовой отчёт ещё не был загружен!");
            } catch (ReportLoadingException e) {
                return new ErrorView("Не удалось загрузить отчёт!");
            } catch (ReportParsingException e) {
                return new ErrorView("Не удалось разобрать отчёт!");
            } catch (ReportValidationException e) {
                return new ErrorView(
                        String.format("Данные в отчётах не сходятся. Проблемный месяц: %s%n",
                                e.getMonthCausedAt()));
            } catch (Throwable throwable) {
                return new ErrorView("Что-то пошло не так :(");
            }
        }
    }

    private String getHandlerNameForCommand(Command command) {
        Class<?> aClass = command.getClass();
        String commandName = aClass.getSimpleName();
        return "handle" + commandName;
    }

    private View handleLoadMonthlyReportsCommand(LoadMonthlyReportsCommand command) {
        helper.loadMonthlyReports();
        return new SuccessView("Месячные отчёты успешно загружены!");
    }

    private View handleLoadYearlyReportCommand(LoadYearlyReportCommand command) {
        helper.loadYearlyReport();
        return new SuccessView("Годовой отчёт успешно загружен!");
    }

    private View handlePrintMonthlyReportsCommand(PrintMonthlyReportsCommand command) {
        MonthlyReportPresentation presentation = helper.getMonthlyReportsPresentation();
        return new MonthlyReportsView(presentation);
    }

    private View handlePrintYearlyReportCommand(PrintYearlyReportCommand command) {
        YearlyReportPresentation presentation = helper.getYearlyReportPresentation();
        return new YearlyReportView(presentation);
    }

    private View handleValidateReportsCommand(ValidateReportsCommand command) {
        helper.validateReports();
        return new SuccessView("Всё сходится, отчёты в порядке!");
    }
}
