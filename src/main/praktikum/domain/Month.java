package praktikum.domain;

public enum Month {
    JANUARY("Январь"),
    FEBRUARY("Февраль"),
    MARCH("Март"),
    APRIL("Апрель"),
    MAY("Май"),
    JUNE("Июнь"),
    JULY("Июль"),
    AUGUST("Август"),
    SEPTEMBER("Сентябрь"),
    OCTOBER("Октябрь"),
    NOVEMBER("Ноябрь"),
    DECEMBER("Декабрь");

    private final String name;

    Month(String name) {
        this.name = name;
    }

    /**
     * @param monthNumber In range from 1 to 12
     */
    public static Month fromInteger(int monthNumber) {
        if (monthNumber < 1 || monthNumber > 12) {
            throw new IllegalArgumentException("Month number should be in range from 1 to 12");
        }

        return Month.values()[monthNumber - 1];
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
