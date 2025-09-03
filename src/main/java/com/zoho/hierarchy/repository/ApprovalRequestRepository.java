package com.zoho.hierarchy.repository;

import com.zoho.hierarchy.dto.ApprovalRequest;

import java.util.Optional;

public interface ApprovalRequestRepository extends CrudRepository<ApprovalRequest, Long> {
    Optional<ApprovalRequest> findByReportingId(long id) throws Exception;
    Optional<ApprovalRequest> findByUserId(long userId) throws Exception;
}
