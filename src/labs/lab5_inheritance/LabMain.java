package labs.lab5_inheritance;

import java.util.ArrayList;
import java.util.HashMap;

public class LabMain {
    public static void main() {
        ArrayList<Schoolboy> schoolboys = new ArrayList<>();
        populateSchoolboys(schoolboys);
        ArrayList<Undergrad> undergrads = new ArrayList<>();
        populateUndergrads(undergrads);

        femalePrizers(schoolboys);
        undergradsWithoutTails(undergrads);
        
        ArrayList<Student> students = combineAllStudents(schoolboys, undergrads);
        studentsWithScolarship(students);
    }

    static ArrayList<Student> combineAllStudents(ArrayList<Schoolboy> schoolboys, ArrayList<Undergrad> undergrads) {
        ArrayList<Student> students = new ArrayList<>();
        for (Schoolboy schoolboy : schoolboys) {
            students.add(schoolboy);
        }
        for (Undergrad undergrad : undergrads) {
            students.add(undergrad);
        }
        return students;
    }

    static ArrayList<Student> studentsWithScolarship(ArrayList<Student> students) {
        ArrayList<Student> withScolarship;
        withScolarship = students.stream().filter(o -> o.isScolarshipEligible())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        for (Student student : withScolarship) {
            System.out.println(student);
        }
        return withScolarship;
    }

    static ArrayList<Schoolboy> femalePrizers(ArrayList<Schoolboy> schoolboys) {
        ArrayList<Schoolboy> femalePrizers;
        femalePrizers = schoolboys.stream().filter(o -> o.isFemale() && o.isAwardedFirstPlace())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        for (Schoolboy schoolboy : femalePrizers) {
            System.out.println(schoolboy);
        }
        return femalePrizers;
    }

    static ArrayList<Undergrad> undergradsWithoutTails(ArrayList<Undergrad> undergrads) {
        ArrayList<Undergrad> withoutTails;
        withoutTails = undergrads.stream().filter(o -> o.getCourseGrades().size() > 0)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        for (Undergrad undergrad : withoutTails) {
            System.out.println(undergrad);
        }
        return withoutTails;
    }

    static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    static int getRandomGrade() {
        return getRandom(4, 6);
    }

    static void populateUndergrads(ArrayList<Undergrad> undergrads) {
        for (int i = 0; i < 10; i++) {
            int rngAge = getRandom(18, 50);
            Gender gender = getRandom(0, 2) == 0 ? Gender.MALE : Gender.FEMALE;
            ArrayList<Integer> grades = new ArrayList<>();
            ArrayList<Integer> courseGrades = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                grades.add(getRandomGrade());
            }
            for (int j = 0; j < getRandom(1, 5); j++) {
                courseGrades.add(getRandomGrade());
            }

            undergrads.add(new Undergrad("Name" + i, rngAge, gender, grades, courseGrades));
        }
    }

    static void populateSchoolboys(ArrayList<Schoolboy> schoolboys) {
        for (int i = 0; i < 10; i++) {
            int rngAge = getRandom(10, 18);
            HashMap<CoreSubjects, Integer> grades = new HashMap<>();
            ArrayList<Integer> otherGrades = new ArrayList<>();
            ArrayList<OlympiadAward> awards = new ArrayList<>();
            Gender gender = getRandom(0, 2) == 0 ? Gender.MALE : Gender.FEMALE;
            grades.put(CoreSubjects.MATH, getRandomGrade());
            grades.put(CoreSubjects.RUSSIAN, getRandomGrade());
            grades.put(CoreSubjects.HISTORY, getRandomGrade());
            grades.put(CoreSubjects.ENGLISH, getRandomGrade());
            for (int j = 0; j < 6; j++) {
                otherGrades.add(getRandomGrade());
            }
            for (int j = 0; j < 3; j++) {
                OlympiadType type = OlympiadType.values()[getRandom(0, 3)];
                int place = getRandom(0, 5);
                awards.add(new OlympiadAward(type, "Name" + i, place));
            }

            schoolboys.add(new Schoolboy("Name" + i, rngAge, gender, grades, otherGrades, awards));
        }
    }
}
