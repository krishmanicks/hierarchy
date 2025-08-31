package com.zoho.hierarchy.dto;

public class Hierarchy {

    private long id;

    private long userId;

    private long reportingTo;

    public long getId() {
        return id;
    }

    public Hierarchy setId(long id) {
        this.id = id;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public Hierarchy setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getReportingTo() {
        return reportingTo;
    }

    public Hierarchy setReportingTo(long reportingTo) {
        this.reportingTo = reportingTo;
        return this;
    }
}
