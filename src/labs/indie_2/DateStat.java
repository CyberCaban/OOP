package labs.indie_2;

import java.util.Date;
import java.util.Locale;

public class DateStat {
    private String type;
    private Date date;
    private WindRose mostAppearingWindDir;
    private double maxWindSpeed;
    private WindRose maxWindDir;

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMostAppearingWindDir(WindRose mostAppearingWindDir) {
        this.mostAppearingWindDir = mostAppearingWindDir;
    }

    public void setMaxWindSpeed(double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public void setMaxWindDir(WindRose maxWindDir) {
        this.maxWindDir = maxWindDir;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public WindRose getMostAppearingWindDir() {
        return mostAppearingWindDir;
    }

    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public WindRose getMaxWindDir() {
        return maxWindDir;
    }

    public DateStat(String type, Date date, WindRose mostAppearingWindDir,
            double maxWindSpeed, WindRose maxWindDir) {
        this.type = type;
        this.date = date;
        this.mostAppearingWindDir = mostAppearingWindDir;
        this.maxWindSpeed = maxWindSpeed;
        this.maxWindDir = maxWindDir;
    }

    public String toJson() {
        return String.format(Locale.US,
                "{\"mostAppearingWindDir\":\"%s\",\"maxWindSpeed\":%.2f,\"maxWindDir\":\"%s\"}",
                mostAppearingWindDir, maxWindSpeed, maxWindDir);
    }

}