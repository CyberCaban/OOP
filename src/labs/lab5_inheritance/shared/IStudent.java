package labs.lab5_inheritance.shared;

import labs.lab5_inheritance.enums.Gender;

public interface IStudent {
    String getName();

    int getAge();

    Gender getGender();

    boolean isMale();

    boolean isFemale();

    boolean isOther();

    boolean isScolarshipEligible();
}
