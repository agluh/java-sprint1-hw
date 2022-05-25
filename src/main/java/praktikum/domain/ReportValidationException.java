package praktikum.domain;

public class ReportValidationException extends RuntimeException {
    private final Month month;

    public ReportValidationException(Month month) {
        this.month = month;
    }

    public Month getMonthCausedAt() {
        return month;
    }
}
