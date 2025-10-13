package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.content.Context;
import android.widget.Toast;

class Verifier {
    private static Verifier instance;

    private Verifier() {
    }

    public static Verifier getInstance() {
        if (instance == null) {
            instance = new Verifier();
        }
        return instance;
    }

    public boolean verifyStudentId(Context c, String studentId) {
        // TODO: NOT IMPLEMENTED ;
        return false;
    }

    public boolean verifyName(Context c, String studentName) {
        // TODO: NOT IMPLEMENTED ;
        return false;
    }

    public boolean verifyEmail(Context c, String email) {
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}(?:\\.[A-Za-z]{2,})*$")) {
            Toast.makeText(c, "Invalid Email Address", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public boolean verifyPhone(Context c, String phone) {
        // TODO: NOT IMPLEMENTED ;
        return false;
    }

    public boolean verifyCourseCode(Context c, String courseCode) {
        // TODO: NOT IMPLEMENTED ;
        return false;
    }

    public boolean verifyDate(Context c, String date) {
        // TODO: NOT IMPLEMENTED ;
        return false;
    }
}