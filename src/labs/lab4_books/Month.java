package labs.lab4_books;

public enum Month {
    JANUARY(1), FEBRUARY(2), MARCH(3), APRIL(4), MAY(5), JUNE(6), JULY(7), AUGUST(8), SEPTEMBER(9), OCTOBER(10),
    NOVEMBER(11), DECEMBER(12);

    private final int value;

    private Month(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean isGreaterThan(Month month1, Month month2) {
        return month1.getValue() > month2.getValue();
    }

    public static boolean isLessThan(Month month1, Month month2) {
        return month1.getValue() < month2.getValue();
    }

    public static boolean isEqual(Month month1, Month month2) {
        return month1.getValue() == month2.getValue();
    }
}