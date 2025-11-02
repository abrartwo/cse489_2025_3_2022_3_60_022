package edu.edubd.cse489_2025_3_2022_3_60_022.helpers;


public class StudentBuilder {
    String id, name, email, phoneNumber;

    public StudentBuilder addId(String id) {
        this.id = id;
        return this;
    }

    public StudentBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder addEmail(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder addPhone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Student build() {
        return new Student(this);
    }
}