package edu.edubd.cse489_2025_3_2022_3_60_022;

public abstract class Validator {
    public static boolean validateStudentId(ThenDoEvent event, String studentId) {
        if (studentId.isEmpty()) {
            event.callback("Student ID is required");
            return false;
        }
        var isValid = true;
        String[] tokens = studentId.split("-");

        if (studentId.length() < 13) {
            isValid = false;
        } else if (tokens.length != 4) {
            isValid = false;
        } else {
            for (var tok : tokens) {
                try {
                    Integer.parseInt(tok);
                } catch (NumberFormatException nfe) {
                    isValid = false;
                }
            }
        }

        if (!isValid) {
            event.callback("Invalid Student ID");
        }
        return isValid;
    }

    public static boolean validateName(ThenDoEvent event, String studentName) {
        if (studentName.isEmpty()) {
            event.callback("Student name is required");
            return false;
        }
        if (studentName.length() < 2) {
            event.callback("Name is too short. Born again and change your name.");
            return false;
        }
        if (studentName.matches(".*\\d.*")) {
            event.callback("You have a number on your name??????.");
            return false;
        }
        return true;
    }

    public static boolean validateEmail(ThenDoEvent event, String email) {
        if (email.isEmpty()) {
            event.callback("Student Email is required");
            return false;
        }
        var isValid = true;
        String[] tokens = email.split("@");

        if (email.length() < 5) {
            isValid = false;
        } else if (tokens.length != 2) {
            isValid = false;
        } else if (tokens[1].split("\\.").length != 2) {
            isValid = false;
        }

        if (!isValid) {
            event.callback("Not an email");
        }
        return isValid;
    }

    public static boolean validatePhone(ThenDoEvent event, String phone) {
        if (phone.isEmpty()) {
            event.callback("Phone number is required");
            return false;
        }
        if (phone.length() < 10 || phone.length() > 20) {
            event.callback("Phone must be under 10 to 20 char long");
            return false;
        } else if (phone.matches(".*[a-zA-Z].*")) {
            event.callback("Phone can not contain alphabetic character.");
            return false;
        }
        return true;
    }

    public abstract boolean validateAllIfNotThenDo(ThenDoEvent event);

    public interface ThenDoEvent {
        void callback(String info);
    }


}