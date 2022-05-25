package praktikum.domain;

import praktikum.io.ReportLoadingException;
import praktikum.io.ReportParsingException;

public interface MonthlyReportRepository {

    /**
     * Fetch monthly report by date.
     *
     * @throws ReportLoadingException in case of IO errors
     * @throws ReportParsingException in case of parsing errors
     */
    MonthlyReport getByDate(int year, Month month);
}
