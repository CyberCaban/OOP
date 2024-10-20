package labs.lab6_containers;

import java.util.ArrayList;
import java.util.Comparator;

public class MarkJournal {
    private int number;
    private ArrayList<SchoolBoy> schoolBoys = new ArrayList<>();

    public MarkJournal(int number) throws IllegalArgumentException {
        setNumber(number);
    }

    public MarkJournal(int number, ArrayList<SchoolBoy> schoolBoys)
            throws IllegalArgumentException {
        setNumber(number);
        setSchoolBoys(schoolBoys);
    }

    public void addSchoolBoy(SchoolBoy schoolBoy)
            throws IllegalArgumentException {
        if (schoolBoys.contains(schoolBoy)) {
            throw new IllegalArgumentException("School boy already exists");
        }
        schoolBoys.add(schoolBoy);
    }

    double getAverage() {
        double sum = 0;
        for (SchoolBoy schoolBoy : schoolBoys) {
            sum += schoolBoy.getMark();
        }
        return sum / schoolBoys.size();
    }

    @Override
    public String toString() {
        return "Grade " + number + ": " + '\n' + schoolBoys;
    }

    public ArrayList<SchoolBoy> getSchoolBoys() {
        return schoolBoys;
    }

    public void setSchoolBoys(ArrayList<SchoolBoy> schoolBoys) {
        this.schoolBoys = schoolBoys;
    }

    public void addSchoolBoys(ArrayList<SchoolBoy> schoolBoys) {
        this.schoolBoys.addAll(schoolBoys);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws IllegalArgumentException {
        if (number < 0 || number > 11) {
            throw new IllegalArgumentException(
                    "Number must be between 0 and 11");
        }
        this.number = number;
    }

}

class MarkJournalAvgComparator implements Comparator<MarkJournal> {
    @Override
    public int compare(MarkJournal o1, MarkJournal o2) {
        if (o1.getAverage() > o2.getAverage()) {
            return 1;
        } else if (o1.getAverage() < o2.getAverage()) {
            return -1;
        } else {
            return 0;
        }
    }
}