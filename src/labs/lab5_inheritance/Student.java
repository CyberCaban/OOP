package labs.lab5_inheritance;

abstract public class Student {
    String name;
    int age;
    Gender gender;

    public Student(String name, int age, Gender genger) {
        this.name = name;
        this.age = age;
        this.gender = genger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender genger) {
        this.gender = genger;
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
}
