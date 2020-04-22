package com.ukeess.dao.impl;

import com.ukeess.dao.GenericDAO;
import com.ukeess.entity.Department;
import com.ukeess.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

import static com.ukeess.model.constant.SQLStatements.DEPARTMENT_SELECT_ALL;
import static com.ukeess.model.constant.SQLStatements.DEPARTMENT_SELECT_BY_ID;
import static com.ukeess.model.constant.SQLStatements.DEPARTMENT_TOTAL_COUNT_BY_ID;

/**
 * @author Nazar Lelyak.
 */
@Repository
public class DepartmentDAO extends NamedParameterJdbcDaoSupport implements GenericDAO<Department> {

    @Autowired
    public void setJt(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    private RowMapper<Department> getEntityRowMapper() {
        return (rs, rowNum) -> Department.builder()
                .id(rs.getInt("dpID"))
                .name(rs.getString("dpName"))
                .build();
    }

    @Override
    public Optional<Department> getById(int id) {
        if (exists(id)) {
            return Optional.ofNullable(
                    getNamedParameterJdbcTemplate().queryForObject(
                            DEPARTMENT_SELECT_BY_ID,
                            getIdParameterSource(id),
                            getEntityRowMapper())
            );
        }
        throw new EntityNotFoundException(id);
    }

    @Override
    public Collection<Department> getAll() {
        return getNamedParameterJdbcTemplate().query(DEPARTMENT_SELECT_ALL, getEntityRowMapper());
    }

    private boolean exists(int id) {
        return getNamedParameterJdbcTemplate()
                .queryForObject(DEPARTMENT_TOTAL_COUNT_BY_ID,
                        getIdParameterSource(id),
                        Integer.class) > 0;
    }

    @Override
    public Department save(Department department) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException();
    }
}
