package com.ukeess.dao.impl;

import com.ukeess.dao.BaseDAO;
import com.ukeess.entity.Department;
import com.ukeess.exception.EntityNotFoundException;
import com.ukeess.model.constant.SqlQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
@Repository
public class DepartmentDAO extends NamedParameterJdbcDaoSupport implements BaseDAO<Department> {

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
                            SqlQueries.DEPARTMENT_SELECT_BY_ID,
                            getIdParameterSource(id),
                            getEntityRowMapper())
            );
        }
        throw new EntityNotFoundException(id);
    }

    public List<Department> getAll() {
        return getNamedParameterJdbcTemplate()
                .query(SqlQueries.DEPARTMENT_SELECT_ALL, getEntityRowMapper());
    }

    private boolean exists(int id) {
        return getNamedParameterJdbcTemplate()
                .queryForObject(SqlQueries.DEPARTMENT_TOTAL_COUNT_BY_ID,
                        getIdParameterSource(id),
                        Integer.class) > 0;
    }

    @Override
    public Page<Department> getAll(Pageable pageable) {
        throw new UnsupportedOperationException();
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
