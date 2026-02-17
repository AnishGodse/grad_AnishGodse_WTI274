package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Student {
    @Id
    private int univRegNo;
    private int rollNo;
    private String name;
    private int standard;
    private int percentage;
    private String school;
    private String gender;

    public Student(String gender, String name, int percentage, int rollNo, String school, int standard, int univRegNo) {
        this.gender = gender;
        this.name = name;
        this.percentage = percentage;
        this.rollNo = rollNo;
        this.school = school;
        this.standard = standard;
        this.univRegNo = univRegNo;
    }

    public Student(){

    }

    public int getUnivRegNo() {
        return univRegNo;
    }

    public void setUnivRegNo(int univRegNo) {
        this.univRegNo = univRegNo;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{");
        sb.append("univRegNo=").append(univRegNo);
        sb.append(", rollNo=").append(rollNo);
        sb.append(", name=").append(name);
        sb.append(", standard=").append(standard);
        sb.append(", percentage=").append(percentage);
        sb.append(", school=").append(school);
        sb.append(", gender=").append(gender);
        sb.append('}');
        return sb.toString();
    }


}
