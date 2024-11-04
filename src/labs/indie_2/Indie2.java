package labs.indie_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import labs.indie_2.Drawing.*;

public class Indie2 {
    private static final String INPUT_FILE = "data_weather.txt";
    private static final String SRC_LOCATION = "src/labs/indie_2/";
    private static final ArrayList<WeatherOfTheDay> weather = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    static void readFile(ArrayList<String> buf, String filename)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            buf.add(line);
        }
        br.close();
    }

    public static void main() {
        ArrayList<String> buf = new ArrayList<>();
        try {
            readFile(buf, SRC_LOCATION + INPUT_FILE);
        } catch (IOException e) {
            System.out.println("Error reading file");
            System.exit(1);
        }

        // date format yyyy-mm-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final int DATA_OFFSET = 10;
        for (int i = 0; i < buf.size(); i += DATA_OFFSET) {
            int j = i;
            try {
                Date date = sdf.parse(buf.get(j++));
                String location = buf.get(j++);
                double minTemp = parseDouble(buf.get(j++));
                double maxTemp = parseDouble(buf.get(j++));
                double rainFall = parseDouble(buf.get(j++));
                double evaporation = parseDouble(buf.get(j++));
                double sunshine = parseDouble(buf.get(j++));
                WindRose windRose = WindRose.fromString(buf.get(j++));
                double maxWindSpeed = parseDouble(buf.get(j++));
                WindRose windDirection = WindRose.fromString(buf.get(j++));

                weather.add(new WeatherOfTheDay(date, location, minTemp,
                        maxTemp, rainFall, evaporation, sunshine, windRose,
                        maxWindSpeed, windDirection));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error getting data on idx: " + j);
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Error formatting data on idx: " + j);
                continue;
            } catch (ParseException e) {
                System.out.println("Error getting data on idx: " + j);
                continue;
            }
        }

        weatherHistogram();

        weekendWeather();

    }

    static void weatherHistogram() {
        Map<String, List<Integer>> windDirectionMap = new HashMap<>();
        for (WeatherOfTheDay w : weather) {
            if (!windDirectionMap
                    .containsKey(w.getWindDirection().toString())) {
                windDirectionMap.put(w.getWindDirection().toString(),
                        new ArrayList<>());
            }
            windDirectionMap.get(w.getWindDirection().toString())
                    .add(((int) w.getMaxWindSpeed()));
        }
        Histogram histogram = new Histogram(windDirectionMap);
        histogram.printHistogram();
    }

    static double parseDouble(String str) {
        if (str.isEmpty() || str.equals("NA")) {
            return 0;
        }
        return Double.parseDouble(str);
    }

    static void weekendWeather() {
        System.out.println("Enter date (yyyy-mm-dd): ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Error parsing date");
            return;
        }

        for (int i = 0; i < weather.size(); i++) {
            WeatherOfTheDay w = weather.get(i);
            if (w.getDate().equals(date)) {
                Calendar cl = Calendar.getInstance();
                cl.setTime(w.getDate());
                int dayOfWeek = cl.get(Calendar.DAY_OF_WEEK);
                int dayOfWeekDiffWeekend = dayOfWeek < Calendar.SATURDAY
                        ? Calendar.SATURDAY - dayOfWeek
                        : 0;

                WeatherOfTheDay closestSatDay = weather
                        .get(i + dayOfWeekDiffWeekend);
                WeatherOfTheDay closestSunDay = weather
                        .get(i + dayOfWeekDiffWeekend + 1);

                WeatherReport wp = WeatherReport.generateReport(closestSatDay,
                        closestSunDay);

                wp.printReport();

                return;
            }
        }
        System.out.println("No data for this date");
        weekendWeather();
    }

    /* @formatter:off
     * В файле data_weather.txt содержится информация о погоде каждого дня 2009
     * - 2016 гг.: 
     * 1. дата наблюдения,
     * 2. название метеостанции,
     * 3. минимальнаятемпература в градусах цельсия, 
     * 4. максмимальная температура в градусах цельсия, 
     * 5. количество осадков, зафиксированных за день в мм., 
     * 6. т.н испарение на сковороде за 24 часа, 
     * 7. количество часов яркого солнечного света в день, 
     * 8. направление сильнейшего порыва ветра, 
     * 9. скорость (км/ч) самого сильного порыва ветра, 
     * 10. направление ветра в 9 утра (NA - значение параметра не определено). 
     * Каждый из десяти параметров расположен в
     * отдельной строке, в следующих десяти строказ - информация о следующем
     * объекте. 
     * (1)✅ Выведите на консоль гистограмму - аналог розы ветров для каждого
     * года отдельно и для всего периода. 
     * (2)✅ По заданной пользователем дате определите погоду на ближайшие выходные:
     * диапазон и среднюю температуру,
     * осадки (сильным называется дождь, если его выпадает от 15 до 49 мм, очень
     * сильным когда их выпадает от 50, сильный снег - от 7 до 19 мм, очень
     * сильный - > 20 мм), направление ветра. 
     * (3) Для каждого месяца и года найдите
     * наиболее частое направление ветра, максимальную скорость ветра и
     * соответствующую ему направление. Выведите результат поиска на консоль и в
     * файл в формате json.
     */
}
