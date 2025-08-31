package com.zoho.hierarchy.repository;

import com.zoho.hierarchy.dto.User;


import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String name) throws Exception;
}
