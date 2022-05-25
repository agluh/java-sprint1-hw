package praktikum.domain;

import praktikum.io.ReportLoadingException;
import praktikum.io.ReportParsingException;

public interface YearlyReportRepository {

    /**
     * Fetch yearly report by date.
     *
     * @throws ReportLoadingException in case of IO errors
     * @throws ReportParsingException in case of parsing errors
     */
    YearlyReport getByDate(int year);
}
