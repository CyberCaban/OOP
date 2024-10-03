package labs.lab5_inheritance;

import java.util.ArrayList;

public class Undergrad extends Student {
    ArrayList<Integer> grades;
    ArrayList<Integer> courseGrades;

    public Undergrad(String name, int age, Gender genger) {
        super(name, age, genger);
        grades = new ArrayList<>();
        courseGrades = new ArrayList<>();
    }

    public Undergrad(String name, int age, Gender genger, ArrayList<Integer> grades, ArrayList<Integer> courseGrades) {
        super(name, age, genger);
        this.grades = grades;
        this.courseGrades = courseGrades;
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    public ArrayList<Integer> getCourseGrades() {
        return courseGrades;
    }

    public void setGrades(ArrayList<Integer> grades) {
        this.grades = grades;
    }

    public void setCourseGrades(ArrayList<Integer> courseGrades) {
        this.courseGrades = courseGrades;
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public void addCourseGrade(int grade) {
        courseGrades.add(grade);
    }

    @Override
    public String toString() {
        return "Undergrad{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", grades=" + grades +
                ", courseGrades=" + courseGrades +
                '}';
    }

    public boolean isScolarshipEligible() {
        final int MIN_COURSE_GRADE = 5;
        final double MIN_GRADE = 4.75;
        double avg = 0.0;
        for (int i = 0; i < grades.size(); i++) {
            avg += grades.get(i);
        }
        avg /= grades.size();

        boolean isIdealCourse = true;
        for (int i = 0; i < courseGrades.size(); i++) {
            if (courseGrades.get(i) < MIN_COURSE_GRADE) {
                isIdealCourse = false;
                break;
            }
        }
        return avg >= MIN_GRADE && isIdealCourse;
    }
}
