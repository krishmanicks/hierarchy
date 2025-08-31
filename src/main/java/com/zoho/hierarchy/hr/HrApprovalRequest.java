package com.zoho.hierarchy.hr;

public class HrApprovalRequest {

    private long id;

    private long userId;

    public long getId() {
        return id;
    }

    public HrApprovalRequest setId(long id) {
        this.id = id;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public HrApprovalRequest setUserId(long userId) {
        this.userId = userId;
        return this;
    }
}
