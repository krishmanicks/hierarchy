package com.zoho.hierarchy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zoho.hierarchy.enums.Roles;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Long Id;

    private String name;

    private String email;

    private int age;

    private long phoneNumber;

    private String team;

    private String role = Roles.USER;

    private long reportingTo;

    private boolean hrApproval = true;

    private String updatedJson;

    private boolean approvalNeeded = false;

    public Long getId() {
        return Id;
    }

    public User setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getTeam() {
        return team;
    }

    public User setTeam(String team) {
        this.team = team;
        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public long getReportingTo() {
        return reportingTo;
    }

    public User setReportingTo(long reportingTo) {
        this.reportingTo = reportingTo;
        return this;
    }

    public boolean isHrApproval() {
        return hrApproval;
    }

    public User setHrApproval(boolean hrApproval) {
        this.hrApproval = hrApproval;
        return this;
    }

    public String getUpdatedJson() {
        return updatedJson;
    }

    public User setUpdatedJson(String updatedJson) {
        this.updatedJson = updatedJson;
        return this;
    }

    public boolean isApprovalNeeded() {
        return approvalNeeded;
    }

    public User setApprovalNeeded(boolean approvalNeeded) {
        this.approvalNeeded = approvalNeeded;
        return this;
    }
}
