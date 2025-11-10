package edu.edubd.cse489_2025_3_2022_3_60_022;

public class Attendence {
    private String sid;
    private String name;
    private boolean isPresent;

    public Attendence(String sid, String name, boolean isPresent) {
        this.setSid(sid);
        this.setName(name);
        this.setPresent(isPresent);
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}