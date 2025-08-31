package com.zoho.hierarchy.dto;

import java.util.List;

public class UserEntiry {

    private String name;

    private String email;

    private int age;

    private long phoneNumber;

    private String team;

    private String role;

    private String reportingTo;

    private List<String> reportees;

    public String getName() {
        return name;
    }

    public UserEntiry setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntiry setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserEntiry setAge(int age) {
        this.age = age;
        return this;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public UserEntiry setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getTeam() {
        return team;
    }

    public UserEntiry setTeam(String team) {
        this.team = team;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserEntiry setRole(String role) {
        this.role = role;
        return this;
    }

    public String getReportingTo() {
        return reportingTo;
    }

    public UserEntiry setReportingTo(String reportingTo) {
        this.reportingTo = reportingTo;
        return this;
    }

    public List<String> getReportees() {
        return reportees;
    }

    public UserEntiry setReportees(List<String> reportees) {
        this.reportees = reportees;
        return this;
    }
}
