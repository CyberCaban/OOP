package labs.indie_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Indie {
    public static void main() {
        ArrayList<String> rawData = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./src/labs/indie_1/input/data_countries_world.csv"));
            reader.readLine();
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                rawData.add(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        final String csvRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        final CountryData[] countries = new CountryData[rawData.size()];
        for (int i = 0; i < rawData.size(); i++) {
            String[] line = rawData.get(i).split(csvRegex, -1);
            String name = line[0].trim();
            String region = line[1].trim();
            int population = Integer.parseInt(line[2]);
            double area = parseDoubleLocale(line[3]);
            double coastlinePerc = parseDoubleLocale(line[4]);
            double gdp = parseDoubleLocale(line[5]);
            double literacyPerc = parseDoubleLocale(line[6]);
            double birth = parseDoubleLocale(line[7]);
            double death = parseDoubleLocale(line[8]);
            double agriculturePerc = parseDoubleLocale(line[9]);
            double industryPerc = parseDoubleLocale(line[10]);
            double servicePerc = parseDoubleLocale(line[11]);

            countries[i] = new CountryData(name, region, population, area, coastlinePerc, gdp, literacyPerc, birth,
                    death, agriculturePerc, industryPerc, servicePerc);
        }

        HashMap<String, ArrayList<CountryData>> countriesByRegion = new HashMap<>();
        for (CountryData country : countries) {
            if (!countriesByRegion.containsKey(country.getRegion())) {
                countriesByRegion.put(country.getRegion(), new ArrayList<>());
            }
            countriesByRegion.get(country.getRegion()).add(country);
        }

        BufferedWriter wr;
        for (String region : countriesByRegion.keySet()) {
            try {
                wr = new BufferedWriter(new FileWriter("./src/labs/indie_1/output/data_countries_" + region + ".csv"));
                String header = String.format("%s,%s,%s,%s,%s,%s,%s", "Name", "Population Density", "Coastline",
                        "GDP", "GDP Currency", "Uneducated People", "Most Income Activity Sector");
                wr.append(header + "\n");
                for (CountryData country : countriesByRegion.get(region)) {
                    double coastline = country.calcCoastlineLength();
                    String line = coastline == 0 ? "" : String.format("\"%.2f\"", coastline);
                    String tmp = String.format("%s,%d,%s,\"%.2f\",\"%.2f\",\"%.2f\",%s",
                            country.getName(), country.calcPopulationDensity(),
                            line, country.calcAbsGDP(),
                            country.calcAbsGDPCurrency(0.39), country.calcUneducatedPeople(),
                            country.calcMostIncomeActivitySector());
                    wr.append(tmp + "\n");
                }
                wr.close();
            } catch (Exception e) {
                System.out.println(e);
                return;
            }
        }
    }

    static double parseDoubleLocale(String s) {
        if (s.length() == 0) {
            return 0;
        }
        return Double.parseDouble(s.replace("\"", "").replace(",", "."));
    }

    static int parseIntLocale(String s) {
        return Integer.parseInt(s.replace("\"", "").replace(",", "."));
    }
}
/**
 * Задача 44. В файле data_countries_world.csv содержится информация о странах,
 * о
 * каждой
 * стране в отдельной строке. Формат строки следующий: название, регион,
 * численность населения,
 * площадь в квадратных милях, береговая линия (отношение длины береговой линии
 * к площади),
 * ВВП на душу населения в долларах, грамотность в процентах, уровень
 * рождаемости на 1000 чело-
 * век, уровень смертности на 1000 человек, доля сельского хозяйства в ВВП, доля
 * промышленности
 * в ВВП, доля сферы обслуживания в ВВП.
 * 
 * Выведите в отдельный файл для каждого региона список стран, упорядоченный по
 * площади,
 * с названиями и результатами работы, перечисленных выше методов. Если страна
 * не граничит с
 * морем или океаном, то длину береговой линии не выводите.
 * 
 * Дополнительно создайте класс или классы для хранения информации о частях
 * света: европе,
 * азии, африке, америке, австралии. Каждая часть света, должна содержать список
 * регионов, регион
 * — список стран с полной информацией о них. Выведите на консоль информацию о
 * стране, заданной
 * пользователем. Выведите в отдельный файл для каждой части света список стран,
 * упорядоченный
 * по параметру, который указывает пользователь, в том числе, который может быть
 * рассчитан с
 * помощью описанных в задаче методов.
 */