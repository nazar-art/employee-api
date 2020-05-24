package com.ukeess.dao;


import com.ukeess.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Optional;

public interface GenericDAO<E extends BaseEntity> {

    E save(E entity);

    Optional<E> getById(int id);

    Optional<E> getByName(String name);

    Page<E> getAll(Pageable pageable);

    void deleteById(int id);


    default SqlParameterSource getIdParameterSource(int id) {
        return new MapSqlParameterSource("id", id);
    }

    default SqlParameterSource getNameParameterSource(String name) {
        return new MapSqlParameterSource("name", name);
    }

}
