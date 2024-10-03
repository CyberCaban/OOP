package labs.lab5_inheritance;

import labs.lab5_inheritance.enums.Gender;
import labs.lab5_inheritance.shared.IStudent;

abstract public class Student implements IStudent, Comparable<Student> {
    protected String name;
    protected int age;
    protected Gender gender;
    protected final int MAX_GRADE = 5;
    protected final int MIN_GRADE = 2;

    public Student(String name, int age, Gender gender) {
        setName(name);
        setAge(age);
        setGender(gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is empty or null");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age is negative");
        }
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender is null");
        }
        this.gender = gender;
    }

    public boolean isMale() {
        return gender == Gender.MALE;
    }

    public boolean isFemale() {
        return gender == Gender.FEMALE;
    }

    public boolean isOther() {
        return gender == Gender.OTHER;
    }

    public abstract boolean isScolarshipEligible();

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.name);
    }
}
