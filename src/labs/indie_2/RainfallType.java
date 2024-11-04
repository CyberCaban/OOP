package labs.indie_2;

public enum RainfallType {
    None, Rain, HeavyRain, VeryHeavyRain, Snow, HeavySnow, VeryHeavySnow;

    public static RainfallType fromString(String s) {
        if (s.equalsIgnoreCase("None")) {
            return None;
        } else if (s.equalsIgnoreCase("Rain")) {
            return Rain;
        } else if (s.equalsIgnoreCase("HeavyRain")) {
            return HeavyRain;
        } else if (s.equalsIgnoreCase("VeryHeavyRain")) {
            return VeryHeavyRain;
        } else if (s.equalsIgnoreCase("Snow")) {
            return Snow;
        } else if (s.equalsIgnoreCase("HeavySnow")) {
            return HeavySnow;
        } else if (s.equalsIgnoreCase("VeryHeavySnow")) {
            return VeryHeavySnow;
        } else {
            return null;
        }
    }
}
