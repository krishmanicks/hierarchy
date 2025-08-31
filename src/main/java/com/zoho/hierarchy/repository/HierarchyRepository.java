package com.zoho.hierarchy.repository;

import com.zoho.hierarchy.dto.Hierarchy;

import java.util.Optional;

public interface HierarchyRepository extends CrudRepository<Hierarchy, Long> {

    Optional<Hierarchy> nextHierarchy(long reportingToId) throws Exception;
}
