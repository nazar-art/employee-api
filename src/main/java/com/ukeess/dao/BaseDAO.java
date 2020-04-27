package com.ukeess.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Optional;

public interface BaseDAO<E> {

    E save(E entity);

    Optional<E> getById(int id);

    Page<E> getAll(Pageable pageable);

    void deleteById(int id);

    default SqlParameterSource getIdParameterSource(int id) {
        return new MapSqlParameterSource("id", id);
    }
}
