package com.zoho.hierarchy.repository;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<E, ID> {

    <S extends E> S save(S entity) throws Exception;

    Collection<E> findAllById(ID ids) throws Exception;

    Optional<E> findById(ID id) throws Exception;

    Iterable<E> findAll() throws Exception;

    void deleteById(ID id) throws Exception;

    void delete(E entity) throws Exception;

    <S extends E> S update(S entity) throws Exception;
}