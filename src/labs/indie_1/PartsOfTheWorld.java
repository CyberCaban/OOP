package labs.indie_1;

import java.util.ArrayList;
import java.util.HashMap;

public class PartsOfTheWorld {
    // world_part: region[]
    // region: country[]
    private HashMap<String, HashMap<String, ArrayList<CountryData>>> partsOfTheWorld;
    // all countries
    private ArrayList<CountryData> countries;

    // countries by region: region -> country[]
    public PartsOfTheWorld(HashMap<String, ArrayList<CountryData>> countriesByRegion) {
        this.partsOfTheWorld = new HashMap<>();
        for (String region : countriesByRegion.keySet()) {
            String regionName = region;
            switch (region) {
                case "ASIA (EX. NEAR EAST)":
                    regionName = "ASIA";
                    break;
                case "BALTICS":
                    regionName = "EUROPE";
                    break;
                case "C.W. OF IND. STATES":
                    regionName = "OCEANIA";
                    break;
                case "EASTERN EUROPE":
                    regionName = "EUROPE";
                    break;
                case "LATIN AMER. & CARIB":
                    regionName = "AMERICAS";
                    break;
                case "NEAR EAST":
                    regionName = "ASIA";
                    break;
                case "NORTHERN AFRICA":
                    regionName = "AFRICA";
                    break;
                case "NORTHERN AMERICA":
                    regionName = "AMERICAS";
                    break;
                case "OCEANIA":
                    regionName = "OCEANIA";
                    break;
                case "SUB-SAHARAN AFRICA":
                    regionName = "AFRICA";
                    break;
                case "WESTERN EUROPE":
                    regionName = "EUROPE";
                    break;

                default:
                    break;
            }

            if (!this.partsOfTheWorld.containsKey(regionName)) {
                this.partsOfTheWorld.put(regionName, new HashMap<>());
            }
            this.partsOfTheWorld.get(regionName).put(region, countriesByRegion.get(region));
            // this.partsOfTheWorld.put(regionName, new HashMap<>());
            // this.partsOfTheWorld.get(regionName).put(region, countriesByRegion.get(region));
            
        }

        this.countries = new ArrayList<>();
        for (String region : countriesByRegion.keySet()) {
            for (CountryData country : countriesByRegion.get(region)) {
                this.countries.add(country);
            }
        }

    }

    public void printCountries() {
        int i = 1;
        for (CountryData country : countries) {
            System.out.printf("%d. %s. ", i++, country.getName());
            if (i % 5 == 0) {
                System.out.println();
            }
        }
    }

    public void printCountryData(String countryName) {
        for (CountryData country : countries) {
            if (country.getName().equals(countryName)) {
                System.out.println(country.toString());
                return;
            }
        }

        System.out.println("Country not found");
    }

    public ArrayList<CountryData> getCountries() {
        return countries;
    }

    public HashMap<String, HashMap<String, ArrayList<CountryData>>> getPartsOfTheWorld() {
        return partsOfTheWorld;
    }
}
