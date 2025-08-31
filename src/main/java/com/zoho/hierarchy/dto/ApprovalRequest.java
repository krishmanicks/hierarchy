package com.zoho.hierarchy.dto;

public class ApprovalRequest {

    private Long id;

    private Long userId;

    private Long approvalId;

    private String status;

    public Long getId() {
        return id;
    }

    public ApprovalRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public ApprovalRequest setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getApprovalId() {
        return approvalId;
    }

    public ApprovalRequest setApprovalId(Long approvalId) {
        this.approvalId = approvalId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ApprovalRequest setStatus(String status) {
        this.status = status;
        return this;
    }
}
