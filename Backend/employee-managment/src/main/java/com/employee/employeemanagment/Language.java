package com.employee.employeemanagment;

public class Language {

    private String languageName;
    private int scoreOutof100;

    // Constructors, getters, and setters

    public Language() {
    }

    public Language(String languageName, int scoreOutof100) {
        this.languageName = languageName;
        this.scoreOutof100 = scoreOutof100;
    }

    // Getters and setters (generated using Lombok for brevity)

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public int getScoreOutof100() {
        return scoreOutof100;
    }

    public void setScoreOutof100(int scoreOutof100) {
        this.scoreOutof100 = scoreOutof100;
    }

    // toString method for easy printing

    @Override
    public String toString() {
        return "Language{" +
                "languageName='" + languageName + '\'' +
                ", scoreOutof100=" + scoreOutof100 +
                '}';
    }
}

