package labs.lab6_containers;

import java.util.Comparator;

public class SchoolBoy implements Comparable<SchoolBoy> {
    private String name;
    private String surname;
    private int grade;

    private String subject;
    private int mark;

    public SchoolBoy(String name, String surname, int grade, String subject,
            int mark) {
        setName(name);
        setSurname(surname);
        setGrade(grade);
        setSubject(subject);
        setMark(mark);
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + grade + " " + subject + " " + mark;
    }

    @Override
    public int compareTo(SchoolBoy o) {
        return surname.compareTo(o.surname);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is empty or null");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws IllegalArgumentException {
        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("Surname is empty or null");
        }
        this.surname = surname;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) throws IllegalArgumentException {
        if (grade < 1 || grade > 11) {
            throw new IllegalArgumentException(
                    "Grade must be in the range [1, 11]");
        }
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) throws IllegalArgumentException {
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Subject is empty or null");
        }
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) throws IllegalArgumentException {
        if (mark < 0 || mark > 6) {
            throw new IllegalArgumentException(
                    "Mark must be in the range [0, 6]");
        }
        this.mark = mark;
    }

}

class SchoolBoySubjectComparator implements Comparator<SchoolBoy> {
    @Override
    public int compare(SchoolBoy o1, SchoolBoy o2) {
        return o1.getSubject().compareTo(o2.getSubject());
    }
}