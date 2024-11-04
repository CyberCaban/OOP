package labs.indie_2;

import java.util.Date;

public class WeatherOfTheDay {
    private Date date;
    private String location;
    private double minTemp;
    private double maxTemp;
    private double rainFall;
    private double evaporation;
    private double sunshine;
    private WindRose windRose;
    private double maxWindSpeed;
    private WindRose windDirection;

    public WeatherOfTheDay(Date date, String location, double minTemp,
            double maxTemp, double rainFall, double evaporation,
            double sunshine, WindRose windRose, double maxWindSpeed,
            WindRose windDirection) {
        this.date = date;
        this.location = location;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.rainFall = rainFall;
        this.evaporation = evaporation;
        this.sunshine = sunshine;
        this.windRose = windRose;
        this.maxWindSpeed = maxWindSpeed;
        this.windDirection = windDirection;
    }

    public Date getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getRainFall() {
        return rainFall;
    }

    public double getEvaporation() {
        return evaporation;
    }

    public double getSunshine() {
        return sunshine;
    }

    public WindRose getWindRose() {
        return windRose;
    }

    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public WindRose getWindDirection() {
        return windDirection;
    }

}
