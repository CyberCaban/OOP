package labs.lab5_inheritance;

public enum OlympiadType {
    SCHOOL(1), CITY(2), REGION(3);

    private final int value;

    private OlympiadType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}