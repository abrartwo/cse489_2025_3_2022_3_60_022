package edu.edubd.cse489_2025_3_2022_3_60_022.helpers;

import org.jetbrains.annotations.NotNull;

public abstract class Validator {
    public static boolean validateStudentId(ThenDoEvent event, String studentId) {
        if (studentId.isEmpty()) {
            event.callback("Student ID is required");
            return false;
        }
        boolean isValid = true;
        String[] tokens = studentId.split("-");

        if (studentId.length() < 13) {
            isValid = false;
        } else if (tokens.length != 4) {
            isValid = false;
        } else {
            for (String tok : tokens) {
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
        boolean isValid = true;
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

    public static boolean validateUserId(ThenDoEvent event, String userID) {
        if (userID.isEmpty()) {
            event.callback("Password is required");
            return false;
        }
        if (userID.length() < 4 || userID.length() > 12) {
            event.callback("User ID is length must be under 8 to 32 char long");
            return false;
        }
        if (!userID.matches("^[a-z]+$")) {
            event.callback("User ID should only contain lowercase letters (a–z)");
            return false;
        }
        return true;
    }

    public static boolean validatePassword(ThenDoEvent event, String password) {
        if (password.isEmpty()) {
            event.callback("Password is required");
            return false;
        }
        if (password.length() < 8 || password.length() > 32) {
            event.callback("Password is length must be under 8 to 32 char long");
            return false;
        }
        class ReErr {
            final String pattern;
            final String errInfo;
            public  ReErr(String pattern, String errInfo) {
                this.pattern = pattern;
                this.errInfo = errInfo;
            }
            public boolean matches(@NotNull String str) {
                return str.matches(pattern);
            }
            @NotNull
            public String getErrInfo() {
                return errInfo;
            }
        };
        ReErr[] reErrs = {
            new ReErr("(?=.*[A-Z])", "Password must contain at least one uppercase letter"),
            new ReErr("(?=.*[a-z])", "Password must contain at least one lowercase letter"),
            new ReErr("(?=.*\\d)", "Password must contain at least one number"),
            new ReErr("(?=.*[!@#$%^&*(),.?\":{}|<>])", "Password must contain at least one special character"),
        };

        for (ReErr i : reErrs) {
            if (!i.matches(password)) {
                event.callback(i.getErrInfo());
                return false;
            }
        }

        return true;
    }

    public interface ThenDoEvent {
        void callback(String info);
    }


}