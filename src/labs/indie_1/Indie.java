package labs.indie_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Indie {
    static void readFile(ArrayList<String> buf, String filename) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                buf.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            System.exit(1);
        }
    }

    public static void main() {
        ArrayList<String> rawData = new ArrayList<>();
        readFile(rawData, "./src/labs/indie_1/input/data_countries_world.csv");

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

            Comparator<CountryData> comparator = null;
            switch (param) {
                case "Name":
                    comparator = Comparator.comparing(CountryData::getName);
                    break;

                case "Region":
                    comparator = Comparator.comparing(CountryData::getRegion);
                    break;

                case "Population":
                    comparator = Comparator.comparingInt(CountryData::getPopulation);
                    break;

                case "Area":
                    comparator = Comparator.comparingDouble(CountryData::getArea);
                    break;

                case "CoastlinePerc":
                    comparator = Comparator.comparingDouble(CountryData::getCoastlinePerc);
                    break;

                case "GDP":
                    comparator = Comparator.comparingDouble(CountryData::getGdp);
                    break;

                case "LiteracyPerc":
                    comparator = Comparator.comparingDouble(CountryData::getLiteracyPerc);
                    break;

                case "Birth Rate":
                    comparator = Comparator.comparingDouble(CountryData::getBirth);
                    break;

                case "Death Rate":
                    comparator = Comparator.comparingDouble(CountryData::getDeath);
                    break;

                case "Agriculture":
                    comparator = Comparator.comparingDouble(CountryData::getAgriculturePerc);
                    break;

                case "Industry":
                    comparator = Comparator.comparingDouble(CountryData::getIndustryPerc);
                    break;

                case "Service":
                    comparator = Comparator.comparingDouble(CountryData::getServicePerc);
                    break;

                case "PopulationDensity":
                    comparator = Comparator.comparingDouble(CountryData::calcPopulationDensity);
                    break;

                case "CoastlineLength":
                    comparator = Comparator.comparingDouble(CountryData::calcCoastlineLength);
                    break;

                case "AbsGDPCurrency":
                    comparator = Comparator.comparingDouble(o -> o.calcAbsGDPCurrency(1.0));
                    break;

                case "UneducatedPeople":
                    comparator = Comparator.comparingDouble(CountryData::calcUneducatedPeople);
                    break;

                case "MostIncomeActivitySector":
                    comparator = Comparator.comparing(CountryData::calcMostIncomeActivitySector);
                    break;

                default:
                    break;
            }
            if (comparator != null) {
                countriesToSort.sort(comparator);
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