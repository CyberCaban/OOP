package labs.indie_1;

public class CountryData {
    private String name;
    private String region;
    private int population;
    private double area;
    private double coastlinePerc;
    private double gdp;
    private double literacyPerc;
    private double birth;
    private double death;
    private double agriculturePerc;
    private double industryPerc;
    private double servicePerc;

    public CountryData(String name, String region, int population, double area, double coastlinePerc, double gdp,
            double literacyPerc, double birth, double death, double agriculturePerc, double industryPerc,
            double servicePerc) {
        this.name = name;
        this.region = region;
        this.population = population;
        this.area = area;
        this.coastlinePerc = coastlinePerc;
        this.gdp = gdp;
        this.literacyPerc = literacyPerc;
        this.birth = birth;
        this.death = death;
        this.agriculturePerc = agriculturePerc;
        this.industryPerc = industryPerc;
        this.servicePerc = servicePerc;
    }

    public int calcPopulationDensity() {
        return (int) (population / area);
    }

    public double calcCoastlineLength() {
        return coastlinePerc * area;
    }

    public double calcAbsGDP() {
        return gdp + agriculturePerc + industryPerc + servicePerc;
    }

    public double calcAbsGDPCurrency(double exchangeRate) {
        return calcAbsGDP() * exchangeRate;
    }

    public double calcUneducatedPeople() {
        return (double) population * literacyPerc / 1000.0;
    }

    public ActivitySector calcMostIncomeActivitySector() {
        if (agriculturePerc > industryPerc && agriculturePerc > servicePerc) {
            return ActivitySector.AGRICULTURE;
        } else if (industryPerc > agriculturePerc && industryPerc > servicePerc) {
            return ActivitySector.INDUSTRY;
        } else {
            return ActivitySector.SERVICE;
        }
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public int getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public double getCoastlinePerc() {
        return coastlinePerc;
    }

    public double getGdp() {
        return gdp;
    }

    public double getLiteracyPerc() {
        return literacyPerc;
    }

    public double getBirth() {
        return birth;
    }

    public double getDeath() {
        return death;
    }

    public double getAgriculturePerc() {
        return agriculturePerc;
    }

    public double getIndustryPerc() {
        return industryPerc;
    }

    public double getServicePerc() {
        return servicePerc;
    }

    public String toString() {
        return String.format(
                "Name: %s, Region: %s, Population: %d, Area: %.2f, CoastlinePerc: %.2f, Gdp: %.2f, LiteracyPerc: %.2f, Birth: %.2f, Death: %.2f, AgriculturePerc: %.2f, IndustryPerc: %.2f, ServicePerc: %.2f",
                name, region, population, area, coastlinePerc, gdp, literacyPerc, birth, death, agriculturePerc,
                industryPerc, servicePerc);
    }

    public static void printSortingParams() {
        System.out.println(
                "Available sorting params: Name, Region, Population, Area, CoastlinePerc, GDP, LiteracyPerc, Birth, Death, AgriculturePerc, IndustryPerc, ServicePerc, PopulationDensity, CoastlineLength, AbsGDP, AbsGDPCurrency, UneducatedPeople, MostIncomeActivitySector");
    }

    public static String getSortingParams() {
        return "Name, Region, Population, Area, CoastlinePerc, GDP, LiteracyPerc, Birth, Death, AgriculturePerc, IndustryPerc, ServicePerc, PopulationDensity, CoastlineLength, AbsGDP, AbsGDPCurrency, UneducatedPeople, MostIncomeActivitySector";
    }

    public static boolean isSortingParam(String param) {
        String[] params = getSortingParams().split(", ");
        for (String p : params) {
            if (p.trim().equals(param.trim())) {
                return true;
            }
        }
        return false;
    }
}
