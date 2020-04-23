package com.ukeess.dao;


import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Collection;
import java.util.Optional;

public interface BaseDAO<ENTITY> {

    ENTITY save(ENTITY entity);

    Optional<ENTITY> getById(int id);

    Collection<ENTITY> getAll();

    void deleteById(int id);

    default SqlParameterSource getIdParameterSource(int id) {
        return new MapSqlParameterSource("id", id);
    }
}
