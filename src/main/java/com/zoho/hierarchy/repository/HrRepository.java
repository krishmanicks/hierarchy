package com.zoho.hierarchy.repository;

import com.zoho.hierarchy.hr.HrApprovalRequest;

import java.util.Collection;

public interface HrRepository extends CrudRepository<HrApprovalRequest, Long> {
    Collection<HrApprovalRequest> findAll();
    void deleteByUserId(long userId);
}