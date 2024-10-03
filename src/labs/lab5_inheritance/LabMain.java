package labs.lab5_inheritance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;

import labs.lab5_inheritance.enums.CoreSubjects;
import labs.lab5_inheritance.enums.Gender;
import labs.lab5_inheritance.enums.OlympiadType;

import java.util.Collections;

public class LabMain {
    public static void main() {
        ArrayList<Schoolboy> schoolboys = new ArrayList<>();
        populateSchoolboys(schoolboys);
        ArrayList<Undergrad> undergrads = new ArrayList<>();
        populateUndergrads(undergrads);

        /*
         * вывести информацию о девочках, получивших первые места на олимпиадах
         * любого уровня;
         */
        femalePrizers(schoolboys);
        /*
         * вывести информацию о тех у кого есть оценки за курсовые работы;
         */
        undergradsWithoutTails(undergrads);

        /*
         * создать и напечатать общий список школьников и студентов, которые
         * должны получать специальную стипендию
         */
        ArrayList<Student> students = combineAllStudents(schoolboys,
                undergrads);
        ArrayList<Student> studentsWithScolarship = studentsWithScolarship(
                students);
        for (Student student : studentsWithScolarship) {
            System.out.println(student);
        }
        /*
         * создать и напечатать общий массив школьников и студентов, которые
         * должны получить специальную стипендию, отсортировав его по фамилиям;
         */
        Collections.sort(studentsWithScolarship);
        if (studentsWithScolarship.size() > 0)
            System.out.println("Sorted students: ");
        for (Student student : studentsWithScolarship) {
            System.out.println(student);
        }

        /* определить самого лучшего по успеваемости студента и школьника; */
        for (Undergrad undergrad : undergrads) {
            System.out.println(undergrad + " rating: " + undergrad.getRating());
        }
        UndergradRatingComparator comparator = new UndergradRatingComparator();
        Undergrad maxUndergrad = Collections.max(undergrads, comparator);
        System.out.println("Undergrad with max rating: " + maxUndergrad);
        for (Schoolboy schoolboy : schoolboys) {
            System.out.println(
                    schoolboy.getName() + " rating: " + schoolboy.getRating());
        }
        SchoolboyRatingComparator schoolboyComparator = new SchoolboyRatingComparator();
        Schoolboy maxSchoolboy = Collections.max(schoolboys,
                schoolboyComparator);
        System.out.println("Schoolboy with max rating: " + maxSchoolboy);

        /*
         * напечатать список школьников, отсортированных по рейтингу
         * успеваемости, а затем по номеру школы;
         */
        Comparator<Schoolboy> cmp = new SchoolboyRatingComparator()
                .thenComparing(new SchoolboySchoolIdComparator());
        Collections.sort(schoolboys, cmp);
        System.out.println("Sorted schoolboys by rating then school id: ");
        for (Schoolboy schoolboy : schoolboys) {
            System.out.println(schoolboy.getName() + " " + schoolboy.getRating()
                    + " " + schoolboy.getSchoolId());
        }

        /* напечатать список студентов по рейтингу успеваемости. */
        UndergradRatingComparator ratingComparator = new UndergradRatingComparator();
        Collections.sort(undergrads, ratingComparator);
        System.out.println("Sorted undergrads by rating: ");
        for (Undergrad undergrad : undergrads) {
            System.out
                    .println(undergrad.getName() + " " + undergrad.getRating());
        }
    }

    static ArrayList<Student> combineAllStudents(
            ArrayList<Schoolboy> schoolboys, ArrayList<Undergrad> undergrads) {
        ArrayList<Student> students = new ArrayList<>();
        for (Schoolboy schoolboy : schoolboys) {
            students.add(schoolboy);
        }
        for (Undergrad undergrad : undergrads) {
            students.add(undergrad);
        }
        return students;
    }

    static ArrayList<Student> studentsWithScolarship(
            ArrayList<Student> students) {
        ArrayList<Student> withScolarship;
        withScolarship = students.stream().filter(o -> o.isScolarshipEligible())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return withScolarship;
    }

    static ArrayList<Schoolboy> femalePrizers(ArrayList<Schoolboy> schoolboys) {
        ArrayList<Schoolboy> femalePrizers;
        femalePrizers = schoolboys.stream()
                .filter(o -> o.isFemale() && o.isAwardedFirstPlace())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        for (Schoolboy schoolboy : femalePrizers) {
            System.out.println(schoolboy);
        }
        return femalePrizers;
    }

    static ArrayList<Undergrad> undergradsWithoutTails(
            ArrayList<Undergrad> undergrads) {
        ArrayList<Undergrad> withoutTails;
        withoutTails = undergrads.stream()
                .filter(o -> o.getCourseGrades().size() > 0)
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
            undergrads.add(new Undergrad("Name" + i, rngAge, gender, grades,
                    courseGrades));
        }
    }

    static void populateSchoolboys(ArrayList<Schoolboy> schoolboys) {
        for (int i = 0; i < 10; i++) {
            int rngAge = getRandom(10, 18);
            int schoolId = getRandom(1, 4);
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
            schoolboys.add(new Schoolboy("Name" + i, rngAge, gender, schoolId,
                    grades, otherGrades, awards));
        }
    }
}
