package com.audiometer.model;

import java.time.LocalDate;

public class Patient {
    private String fullName;
    private int age;
    private String gender;
    private LocalDate date;
    private String audiologist;

    public Patient() {}

    public Patient(String fullName, int age, String gender, LocalDate date, String audiologist) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.date = date;
        this.audiologist = audiologist;
    }

    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getAudiologist() { return audiologist; }
    public void setAudiologist(String audiologist) { this.audiologist = audiologist; }
}
