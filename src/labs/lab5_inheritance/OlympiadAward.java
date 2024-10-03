package labs.lab5_inheritance;

import labs.lab5_inheritance.enums.OlympiadType;

public class OlympiadAward {

    private OlympiadType type;
    private String name;
    private int place;

    public OlympiadAward(OlympiadType type, String name, int place) {
        if (place <= 0) {
            throw new IllegalArgumentException("Place must be positive");
        }
        this.type = type;
        this.name = name;
        this.place = place;
    }

    public OlympiadType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public boolean isParticipationAward() {
        return place > 0 && place < 3;
    }

    public boolean isPrizeAward() {
        return place >= 3;
    }

    @Override
    public String toString() {
        return "OlympiadAward{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", place=" + place +
                '}';
    }
}

