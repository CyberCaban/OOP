package labs.indie_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Indie {
    public static void main() {
        ArrayList<String> rawData = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./src/labs/indie_1/input/data_countries_world.csv"));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                rawData.add(line);
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e);
            return;
        }

        final String csvRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        final ArrayList<CountryData> countries = new ArrayList<>(rawData.size());
        for (int i = 0; i < rawData.size(); i++) {
            List<String> line = new ArrayList<>(List.of(rawData.get(i).split(csvRegex, -1)));
            try {
                String name = line.get(0).trim().replaceAll("\"", "");
                String region = line.get(1).trim().replaceAll("\"", "");
                int population = Integer.parseInt(line.get(2));
                double area = parseDoubleLocale(line.get(3));
                double coastlinePerc = parseDoubleLocale(line.get(4));
                double gdp = parseDoubleLocale(line.get(5));
                double literacyPerc = parseDoubleLocale(line.get(6));
                double birth = parseDoubleLocale(line.get(7));
                double death = parseDoubleLocale(line.get(8));
                double agriculturePerc = parseDoubleLocale(line.get(9));
                double industryPerc = parseDoubleLocale(line.get(10));
                double servicePerc = parseDoubleLocale(line.get(11));
                CountryData c = new CountryData(name, region, population, area, coastlinePerc, gdp, literacyPerc, birth,
                        death, agriculturePerc, industryPerc, servicePerc);
                countries.add(c);
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse line because of NumberFormatException " + i + ": " + line);
                continue;
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Failed to parse line " + i + ": " + line);
                continue;
            }
        }
        // region: country[]
        final HashMap<String, ArrayList<CountryData>> countriesByRegion = new HashMap<>();
        for (CountryData country : countries) {
            if (!countriesByRegion.containsKey(country.getRegion())) {
                countriesByRegion.put(country.getRegion(), new ArrayList<>());
            }
            countriesByRegion.get(country.getRegion()).add(country);
        }

        PartsOfTheWorld pow = new PartsOfTheWorld(countriesByRegion);
        pow.printCountries();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter country name: ");
        String countryName = scanner.nextLine();
        while (true) {
            if (!pow.countriesContain(countryName.trim())) {
                System.out.println("Invalid input");
                countryName = scanner.nextLine();
                continue;
            }
            break;
        }
        pow.printCountryData(countryName.trim());

        CountryData.printSortingParams();
        System.out.println("Enter parameter: ");
        String temp = scanner.nextLine();
        while (true) {
            if (!CountryData.isSortingParam(temp)) {
                System.out.println("Invalid input");
                temp = scanner.nextLine();
                continue;
            }
            break;
        }
        String param = temp;
        scanner.close();

        BufferedWriter wr;
        for (String worldPart : pow.getPartsOfTheWorld().keySet()) {
            try {
                wr = new BufferedWriter(new FileWriter("./src/labs/indie_1/output/world_data_" + worldPart + ".csv"));
            } catch (IOException e) {
                System.err.println("Failed to create output file for region " + worldPart);
                continue;
            }
            StringBuilder res = new StringBuilder(CountryData.getSortingParams() + "\n");
            ArrayList<CountryData> countriesToSort = new ArrayList<>();
            for (String region : pow.getPartsOfTheWorld().get(worldPart).keySet()) {
                countriesToSort.addAll(pow.getPartsOfTheWorld().get(worldPart).get(region));
            }

            switch (param) {
                case "Name":
                    countriesToSort.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                    break;

                case "Region":
                    countriesToSort.sort((o1, o2) -> o1.getRegion().compareTo(o2.getRegion()));
                    break;

                case "Population":
                    countriesToSort.sort((o1, o2) -> {
                        int population1 = o1.getPopulation();
                        int population2 = o2.getPopulation();
                        return Integer.compare(population1, population2);
                    });
                    break;

                case "Area":
                    countriesToSort.sort((o1, o2) -> {
                        double area1 = o1.getArea();
                        double area2 = o2.getArea();
                        return Double.compare(area1, area2);
                    });
                    break;

                case "CoastlinePerc":
                    countriesToSort.sort((o1, o2) -> {
                        double coastlinePerc1 = o1.getCoastlinePerc();
                        double coastlinePerc2 = o2.getCoastlinePerc();
                        return Double.compare(coastlinePerc1, coastlinePerc2);
                    });
                    break;

                case "GDP":
                    countriesToSort.sort((o1, o2) -> {
                        double gdp1 = o1.getGdp();
                        double gdp2 = o2.getGdp();
                        return gdp1 > gdp2 ? 1 : -1;
                    });
                    break;

                case "LiteracyPerc":
                    countriesToSort.sort((o1, o2) -> {
                        double literacyPerc1 = o1.getLiteracyPerc();
                        double literacyPerc2 = o2.getLiteracyPerc();
                        return Double.compare(literacyPerc1, literacyPerc2);
                    });
                    break;

                case "Birth Rate":
                    countriesToSort.sort((o1, o2) -> o1.getBirth() > o2.getBirth() ? 1 : -1);
                    break;

                case "Death Rate":
                    countriesToSort.sort((o1, o2) -> o1.getDeath() > o2.getDeath() ? 1 : -1);
                    break;

                case "Agriculture":
                    countriesToSort.sort((o1, o2) -> o1.getAgriculturePerc() > o2.getAgriculturePerc() ? 1 : -1);
                    break;

                case "Industry":
                    countriesToSort.sort((o1, o2) -> o1.getIndustryPerc() > o2.getIndustryPerc() ? 1 : -1);
                    break;

                case "Service":
                    countriesToSort.sort((o1, o2) -> o1.getServicePerc() > o2.getServicePerc() ? 1 : -1);
                    break;

                case "PopulationDensity":
                    countriesToSort.sort((o1, o2) -> {
                        double populationDensity1 = o1.calcPopulationDensity();
                        double populationDensity2 = o2.calcPopulationDensity();
                        return Double.compare(populationDensity1, populationDensity2);
                    });
                    break;

                case "CoastlineLength":
                    countriesToSort.sort((o1, o2) -> {
                        double coastlineLength1 = o1.calcCoastlineLength();
                        double coastlineLength2 = o2.calcCoastlineLength();
                        return Double.compare(coastlineLength1, coastlineLength2);
                    });
                    break;

                case "AbsGDPCurrency":
                    countriesToSort
                            .sort((o1, o2) -> o1.calcAbsGDPCurrency(1.0) > o2.calcAbsGDPCurrency(1.0) ? 1 : -1);
                    break;

                case "UneducatedPeople":
                    countriesToSort.sort((o1, o2) -> {
                        double uneducatedPeople1 = o1.calcUneducatedPeople();
                        double uneducatedPeople2 = o2.calcUneducatedPeople();
                        return Double.compare(uneducatedPeople1, uneducatedPeople2);
                    });
                    break;

                case "MostIncomeActivitySector":
                    countriesToSort.sort((o1, o2) -> o1.calcMostIncomeActivitySector()
                            .compareTo(o2.calcMostIncomeActivitySector()));
                    break;

                default:
                    break;
            }

            for (CountryData sortedCountry : countriesToSort) {
                res.append(String.format(
                        "%s,%d,%s,\"%.2f\",\"%.2f\",\"%.2f\",\"%.2f\",\"%.2f\",\"%.2f\",\"%.2f\",\"%.2f\",\"%.2f\",\"%d\",\"%.2f\",\"%.2f\",\"%.2f\",%s\n",
                        sortedCountry.getName(),
                        sortedCountry.getPopulation(),
                        sortedCountry.getRegion(),
                        sortedCountry.getArea(),
                        sortedCountry.getCoastlinePerc(),
                        sortedCountry.getGdp(),
                        sortedCountry.getLiteracyPerc(),
                        sortedCountry.getBirth(),
                        sortedCountry.getDeath(),
                        sortedCountry.getAgriculturePerc(),
                        sortedCountry.getIndustryPerc(),
                        sortedCountry.getServicePerc(),
                        sortedCountry.calcPopulationDensity(),
                        sortedCountry.calcCoastlineLength(),
                        sortedCountry.calcAbsGDPCurrency(1.0),
                        sortedCountry.calcUneducatedPeople(),
                        sortedCountry.calcMostIncomeActivitySector()));
            }

            try {
                wr.write(res.toString());
                wr.close();
            } catch (IOException e) {
                System.err.println("Failed to write to output file");
                continue;
            }
        }

        for (String region : countriesByRegion.keySet()) {
            try {
                wr = new BufferedWriter(new FileWriter("./src/labs/indie_1/output/region_data_" + region + ".csv"));
            } catch (IOException e) {
                System.err.println("Failed to create output file for region " + region);
                continue;
            }
            StringBuilder result = new StringBuilder(String.format("%s,%s,%s,%s,%s,%s,%s,%s\n", "Name", "Population",
                    "Density", "Coastline",
                    "GDP", "GDP Currency", "Uneducated People", "Most Income Activity Sector"));
            for (CountryData cntry : countriesByRegion.get(region)) {
                double coastline = cntry.calcCoastlineLength();
                String line = coastline == 0 ? "" : String.format("\"%.2f\"", coastline);
                String tmp = String.format("%s,%d,%s,\"%.2f\",\"%.2f\",\"%.2f\",%s\n",
                        cntry.getName(), cntry.calcPopulationDensity(),
                        line, cntry.calcAbsGDP(),
                        cntry.calcAbsGDPCurrency(0.39), cntry.calcUneducatedPeople(),
                        cntry.calcMostIncomeActivitySector());
                result.append(tmp);
            }
            try {
                wr.write(result.toString());
                wr.close();
            } catch (IOException e) {
                System.err.println("Failed to write to output file for region " + region);
                continue;
            }
        }
    }

    static double parseDoubleLocale(String s) {
        if (s.isEmpty()) {
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
 * ВВП на душу населения в долларахграмотность в процентах, уровень
 * рождаемости на 1000 чело-
 * век, уровень смертности на 1000 человек, доля сельского хозяйства в ВВП, доля
 * промышленности
 * в ВВП, доля сферы обслуживания в ВВП.
 * <p>
 * Выведите в отдельный файл для каждого региона список стран, упорядоченный по
 * площади,
 * с названиями и результатами работы, перечисленных выше методов. Если страна
 * не граничит с
 * морем или океаном, то длину береговой линии не выводите.
 * <p>
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