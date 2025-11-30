package edu.edubd.cse489_2025_3_2022_3_60_022;

import static android.content.Context.MODE_PRIVATE;


import android.content.Context;
import android.content.SharedPreferences;

public class AccountCredentialManager {
    private final static String nameKey = "signup_info";
    private final static String userIdKey = "USER_ID";
    private final static String remPassKey = "REMEMBER_PASSWORD";
    private final static String remUserKey = "REMEMBER_USER";
    private final static String passwordKey = "PASSWORD";

    private final SharedPreferences sp;

    private AccountCredentialManager(SharedPreferences sharedPreferences) {
        this.sp = sharedPreferences;
    }

    public static AccountCredentialManager openSharedPreferences(Context c) {
        SharedPreferences sp = c.getSharedPreferences(nameKey, MODE_PRIVATE);
        return new AccountCredentialManager(sp);
    }

    public boolean isUserExists() {
        return !getUserId().isEmpty();
    }

    public boolean isRememberPassword() {
        return sp.getBoolean(remPassKey, false);
    }

    public boolean isRememberUser() {
        return sp.getBoolean(remUserKey, false);
    }

    public String getUserId() {
        return sp.getString(userIdKey, "");
    }

    public String getPassword() {
        return sp.getString(passwordKey, "");
    }

    public Editor newEditor() {
         return new Editor(sp.edit());
    }

    public static class Editor {
        private final SharedPreferences.Editor e;

        private Editor(SharedPreferences.Editor e) {
            this.e = e;
        }

        public Editor updateUserId(String value) {
            e.putString(AccountCredentialManager.userIdKey, value);
            return this;
        }

        public Editor updatePassword(String value) {
            e.putString(AccountCredentialManager.passwordKey, value);
            return this;
        }

        public Editor updateRememberPassword(boolean value) {
            e.putBoolean(AccountCredentialManager.remPassKey, value);
            return this;
        }

        public Editor updateRememberUser(boolean value) {
            e.putBoolean(AccountCredentialManager.remUserKey, value);
            return this;
        }

        public void apply() {
            e.apply();
        }
    }


}