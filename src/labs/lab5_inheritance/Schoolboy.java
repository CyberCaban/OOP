package labs.lab5_inheritance;

import java.util.ArrayList;
import java.util.HashMap;

public class Schoolboy extends Student {
    HashMap<CoreSubjects, Integer> grades;
    ArrayList<Integer> otherGrades;
    ArrayList<OlympiadAward> awards;

    public Schoolboy(String name, int age, Gender genger) {
        super(name, age, genger);
        grades = new HashMap<>();
        otherGrades = new ArrayList<>();
        awards = new ArrayList<>();
    }

    public Schoolboy(String name, int age, Gender genger, HashMap<CoreSubjects, Integer> grades,
            ArrayList<Integer> otherGrades, ArrayList<OlympiadAward> awards) {
        super(name, age, genger);
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

    public String toString() {
        return "Schoolboy{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", grades=" + grades +
                ", otherGrades=" + otherGrades +
                ", awards=" + awards +
                '}';
    }

    boolean isAwardedFirstPlace() {
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
            if ((award.getType() == OlympiadType.REGION && award.isParticipationAward())
                    || (award.getType() == OlympiadType.CITY && award.isPrizeAward())
                    || (award.getType() == OlympiadType.SCHOOL && award.getPlace() == 1)) {
                isAwarded = true;
                break;
            }

        }
        return isGoodGrades && isAwarded;
    }
}
