package labs.lab5_inheritance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;

import labs.lab5_inheritance.enums.CoreSubjects;
import labs.lab5_inheritance.enums.Gender;
import labs.lab5_inheritance.enums.OlympiadType;
import labs.lab5_inheritance.shared.IStudent;

interface ISchoolboy extends IStudent {

    HashMap<CoreSubjects, Integer> getGrades();

    ArrayList<Integer> getOtherGrades();

    ArrayList<OlympiadAward> getAwards();

    void setGrades(HashMap<CoreSubjects, Integer> grades);

    void setOtherGrades(ArrayList<Integer> otherGrades);

    void addGrade(CoreSubjects subject, int grade);

    void addOtherGrade(int grade);

    void addAward(OlympiadAward award);

    boolean isAwardedFirstPlace();

    double getRating();
}

public class Schoolboy extends Student implements ISchoolboy {
    HashMap<CoreSubjects, Integer> grades;
    ArrayList<Integer> otherGrades;
    ArrayList<OlympiadAward> awards;
    int schoolId;

    public Schoolboy(String name, int age, Gender genger, int schoolId) {
        super(name, age, genger);
        this.schoolId = schoolId;
        grades = new HashMap<>();
        otherGrades = new ArrayList<>();
        awards = new ArrayList<>();
    }

    public Schoolboy(String name, int age, Gender genger, int schoolId,
            HashMap<CoreSubjects, Integer> grades,
            ArrayList<Integer> otherGrades, ArrayList<OlympiadAward> awards) {
        super(name, age, genger);
        this.schoolId = schoolId;
        this.grades = grades;
        this.otherGrades = otherGrades;
        this.awards = awards;
    }

    public HashMap<CoreSubjects, Integer> getGrades() {
        return grades;
    }

    public ArrayList<Integer> getOtherGrades() {
        return otherGrades;
    }

    public void setGrades(HashMap<CoreSubjects, Integer> grades) {
        this.grades = grades;
    }

    public void setOtherGrades(ArrayList<Integer> otherGrades) {
        this.otherGrades = otherGrades;
    }

    public void addGrade(CoreSubjects subject, int grade) {
        grades.put(subject, grade);
    }

    public void addOtherGrade(int grade) {
        otherGrades.add(grade);
    }

    public void addAward(OlympiadAward award) {
        awards.add(award);
    }

    public ArrayList<OlympiadAward> getAwards() {
        return awards;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String toString() {
        return "Schoolboy{" + "name='" + name + '\'' + ", age=" + age
                + ", gender=" + gender + ", schoolId=" + schoolId + ", grades="
                + grades + ", otherGrades=" + otherGrades + ", awards=" + awards
                + '}';
    }

    public boolean isAwardedFirstPlace() {
        for (OlympiadAward award : awards) {
            if (award.getPlace() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isScolarshipEligible() {
        final int MIN_CORE_GRADE = 5;
        final int MIN_OTHER_GRADE = 4;
        boolean isGoodGrades = true;
        if (!grades.keySet().contains(CoreSubjects.MATH)
                || !grades.keySet().contains(CoreSubjects.RUSSIAN)
                || !grades.keySet().contains(CoreSubjects.HISTORY)
                || !grades.keySet().contains(CoreSubjects.ENGLISH)) {
            isGoodGrades = false;
        }
        for (CoreSubjects subject : grades.keySet()) {
            if (grades.get(subject) < MIN_CORE_GRADE) {
                isGoodGrades = false;
                break;
            }
        }
        for (int grade : otherGrades) {
            if (grade < MIN_OTHER_GRADE) {
                isGoodGrades = false;
                break;
            }
        }

        boolean isAwarded = false;
        for (OlympiadAward award : awards) {
            if ((award.getType() == OlympiadType.REGION
                    && award.isParticipationAward())
                    || (award.getType() == OlympiadType.CITY
                            && award.isPrizeAward())
                    || (award.getType() == OlympiadType.SCHOOL
                            && award.getPlace() == 1)) {
                isAwarded = true;
                break;
            }

        }
        return isGoodGrades && isAwarded;
    }

    public double getRating() {
        final double RATING_MULTIPLIER = 1 / 100.0;
        double rating = 0;
        for (CoreSubjects subject : grades.keySet()) {
            rating += grades.get(subject);
        }
        for (int grade : otherGrades) {
            rating += grade;
        }
        for (OlympiadAward award : awards) {
            switch (award.getPlace()) {
            case 1:
                rating += 10;
                break;
            case 2:
                rating += 5;
                break;
            case 3:
                rating += 3;
                break;

            default:
                rating += 1;
                break;
            }
        }
        return rating * RATING_MULTIPLIER;
    }
}

class SchoolboyRatingComparator implements Comparator<Schoolboy> {
    @Override
    public int compare(Schoolboy o1, Schoolboy o2) {
        if (o1.getRating() > o2.getRating()) {
            return 1;
        } else if (o1.getRating() < o2.getRating()) {
            return -1;
        } else {
            return 0;
        }
    }
}

class SchoolboySchoolIdComparator implements Comparator<Schoolboy> {
    @Override
    public int compare(Schoolboy o1, Schoolboy o2) {
        if (o1.getSchoolId() > o2.getSchoolId()) {
            return 1;
        } else if (o1.getSchoolId() < o2.getSchoolId()) {
            return -1;
        } else {
            return 0;
        }
    }
}