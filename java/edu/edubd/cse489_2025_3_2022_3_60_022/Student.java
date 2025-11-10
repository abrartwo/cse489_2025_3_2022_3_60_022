package edu.edubd.cse489_2025_3_2022_3_60_022;

import androidx.annotation.NonNull;

import java.io.Serializable;

import edu.edubd.cse489_2025_3_2022_3_60_022.helpers.Validator;


public class Student implements Serializable {
    private final String id;
    private final String name;
    private final String email;
    private final String phoneNumber;

    public Student(@NonNull StudentBuilder b) {
        this.id = b.id;
        this.name = b.name;
        this.email = b.email;
        this.phoneNumber = b.phoneNumber;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s", getId(), getName(), getEmail(), getPhoneNumber());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean validateStudentIfNotThenDo(Validator.ThenDoEvent event) {
        if (!Validator.validateStudentId(event, this.getId())) {
            return false;
        }
        if (!Validator.validateName(event, this.getName())) {
            return false;
        }
        if (!Validator.validateEmail(event, this.getEmail())) {
            return false;
        }
        return Validator.validatePhone(event, this.getPhoneNumber());
    }


}