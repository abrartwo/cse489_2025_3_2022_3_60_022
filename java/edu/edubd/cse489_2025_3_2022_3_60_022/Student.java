package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.os.Bundle;

import androidx.annotation.NonNull;

import java.io.Serializable;


public class Student extends Validator implements Serializable {
    private final String id, name, email, phoneNumber;

    public Student(@NonNull StudentBuilder b) {
        this.id = b.id;
        this.name = b.name;
        this.email = b.email;
        this.phoneNumber = b.phoneNumber;
    }

    public boolean validateAllIfNotThenDo(ThenDoEvent event) {
        if (!Validator.validateStudentId(event, this.id)) {
            return false;
        }
        if (!Validator.validateName(event, this.name)) {
            return false;
        }
        if (!Validator.validateEmail(event, this.email)) {
            return false;
        }
        return Validator.validatePhone(event, this.phoneNumber);
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("[%s] [%s] [%s] [%s]", id, name, email, phoneNumber);
    }
}