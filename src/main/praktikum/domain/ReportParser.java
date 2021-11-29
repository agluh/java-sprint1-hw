package praktikum.domain;

import praktikum.io.ReportParsingException;

public interface ReportParser {

    /**
     * Parse string data and populate monthly report
     *
     * @throws ReportParsingException in case of parsing errors
     */
    void populate(MonthlyReport report, String data);

    /**
     * Parse string data and populate yearly report
     *
     * @throws ReportParsingException in case of parsing errors
     */
    void populate(YearlyReport report, String data);
}
