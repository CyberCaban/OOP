package labs.indie_2;

public enum WindRose {
    N, NNE, NE, ENE, E, ESE, SE, SSE, S, SSW, SW, WSW, W, WNW, NW, NNW, NA;

    public static WindRose fromString(String s)
            throws IllegalArgumentException {
        try {
            return WindRose.valueOf(s);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid wind direction: " + s,
                    e);
        }
    }
}
