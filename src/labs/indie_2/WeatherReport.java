package labs.indie_2;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WeatherReport {
    private Date dateStart;
    private Date dateEnd;
    private double avgTemp;
    private double minTemp;
    private double maxTemp;
    private List<WeatherOfTheDay> weatherOfTheDays;
    private TreeMap<Date, Tuple<RainfallType, WindRose>> reportByDays;

    private WeatherReport(Date dateStart, Date dateEnd, double avgTemp,
            double minTemp, double maxTemp,
            List<WeatherOfTheDay> weatherOfTheDays,
            TreeMap<Date, Tuple<RainfallType, WindRose>> reportByDays) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.avgTemp = avgTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.weatherOfTheDays = weatherOfTheDays;
        this.reportByDays = reportByDays;
    }

    static public WeatherReport generateReport(
            List<WeatherOfTheDay> weatherOfTheDays) {
        double avgTemp;
        double minTemp = Double.MAX_VALUE;
        double maxTemp = Double.MIN_VALUE;
        Date minDate = null;
        Date maxDate = null;
        TreeMap<Date, Tuple<RainfallType, WindRose>> reportByDays = new TreeMap<>();
        for (WeatherOfTheDay w : weatherOfTheDays) {
            if (minDate == null || w.getDate().before(minDate)) {
                minDate = w.getDate();
            }
            if (maxDate == null || w.getDate().after(maxDate)) {
                maxDate = w.getDate();
            }

            minTemp = Math.min(minTemp, w.getMinTemp());
            maxTemp = Math.max(maxTemp, w.getMaxTemp());

            RainfallType type = RainfallType.None;
            if (w.getMaxTemp() <= 0) {
                type = RainfallType.Snow;
                if (w.getRainFall() >= 7 && w.getRainFall() <= 19) {
                    type = RainfallType.HeavySnow;
                } else if (w.getRainFall() >= 20) {
                    type = RainfallType.VeryHeavySnow;
                }
            } else {
                type = RainfallType.Rain;
                if (w.getRainFall() >= 15 && w.getRainFall() <= 50) {
                    type = RainfallType.HeavyRain;
                } else if (w.getRainFall() > 50) {
                    type = RainfallType.VeryHeavyRain;
                }
            }

            reportByDays.put(w.getDate(), new Tuple<>(type, w.getWindRose()));
        }
        avgTemp = (minTemp + maxTemp) / 2;

        return new WeatherReport(minDate, maxDate, avgTemp, minTemp, maxTemp,
                weatherOfTheDays, reportByDays);
    }

    public static WeatherReport generateReport(
            WeatherOfTheDay... weatherOfTheDays) {
        return generateReport(Arrays.asList(weatherOfTheDays));
    }

    public void printReport() {
        System.out.println("Weather Report:");
        System.out.println("Date Start: " + dateStart);
        System.out.println("Date End: " + dateEnd);
        System.out.println("Average Temperature: " + avgTemp + " C");
        System.out.println("Minimum Temperature: " + minTemp + " C");
        System.out.println("Maximum Temperature: " + maxTemp + " C");
        System.out.println("Rainfall and Wind Direction:");

        for (Map.Entry<Date, Tuple<RainfallType, WindRose>> entry : reportByDays
                .entrySet()) {
            System.out.println(entry.getKey() + ": (" + entry.getValue().get0()
                    + ", " + entry.getValue().get1() + ")");
        }
    }
}
