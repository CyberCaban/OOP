package labs.lab5_inheritance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import labs.lab5_inheritance.enums.Gender;
import labs.lab5_inheritance.shared.IStudent;

interface IUndergrad extends IStudent {
    List<Integer> getGrades();

    List<Integer> getCourseGrades();

    void setGrades(ArrayList<Integer> grades);

    void setCourseGrades(ArrayList<Integer> courseGrades);

    void addGrade(int grade);

    void addCourseGrade(int grade);

    boolean isScolarshipEligible();

    double getRating();
}

public class Undergrad extends Student implements IUndergrad {
    private ArrayList<Integer> grades;
    private ArrayList<Integer> courseGrades;

    public Undergrad(String name, int age, Gender genger) {
        super(name, age, genger);
        grades = new ArrayList<>();
        courseGrades = new ArrayList<>();
    }

    public Undergrad(String name, int age, Gender genger,
            ArrayList<Integer> grades, ArrayList<Integer> courseGrades) {
        super(name, age, genger);
        setGrades(grades);
        setCourseGrades(courseGrades);
    }

    public List<Integer> getGrades() {
        return Collections.unmodifiableList(grades);
    }

    public List<Integer> getCourseGrades() {
        return Collections.unmodifiableList(courseGrades);
    }

    public void setGrades(ArrayList<Integer> grades) {
        if (grades == null) {
            throw new NullPointerException("Grades cannot be null");
        }
        for (int grade : grades) {
            if (grade < MIN_GRADE || grade > MAX_GRADE) {
                throw new IllegalArgumentException(
                        "Grades must be in the range [" + MIN_GRADE + ", "
                                + MAX_GRADE + "]");
            }
        }
        this.grades = grades;
    }

    public void setCourseGrades(ArrayList<Integer> courseGrades) {
        if (courseGrades == null) {
            throw new NullPointerException("CourseGrades cannot be null");
        }
        for (int grade : courseGrades) {
            if (grade < MIN_GRADE || grade > MAX_GRADE) {
                throw new IllegalArgumentException(
                        "Grades must be in the range [" + MIN_GRADE + ", "
                                + MAX_GRADE + "]");
            }
        }
        this.courseGrades = courseGrades;
    }

    public void addGrade(int grade) {
        if (grade < MIN_GRADE || grade > MAX_GRADE) {
            throw new IllegalArgumentException("Grades must be in the range ["
                    + MIN_GRADE + ", " + MAX_GRADE + "]");
        }
        grades.add(grade);
    }

    public void addCourseGrade(int grade) {
        if (grade < MIN_GRADE || grade > MAX_GRADE) {
            throw new IllegalArgumentException("Grades must be in the range ["
                    + MIN_GRADE + ", " + MAX_GRADE + "]");
        }
        courseGrades.add(grade);
    }

    @Override
    public String toString() {
        return "Undergrad{" + "name='" + name + '\'' + ", age=" + age
                + ", gender=" + gender + ", grades=" + grades
                + ", courseGrades=" + courseGrades + '}';
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

    public double getRating() {
        double rating = 0;
        final double RATING_MULTIPLIER = 1 / 100.0;
        for (int i = 0; i < courseGrades.size(); i++) {
            rating += (double) courseGrades.get(i);
        }
        for (int i = 0; i < grades.size(); i++) {
            rating += (double) grades.get(i);
        }
        return rating * RATING_MULTIPLIER;
    }
}

class UndergradRatingComparator implements Comparator<Undergrad> {
    @Override
    public int compare(Undergrad o1, Undergrad o2) {
        if (o1.getRating() > o2.getRating()) {
            return 1;
        } else if (o1.getRating() < o2.getRating()) {
            return -1;
        } else {
            return 0;
        }
    }
}
