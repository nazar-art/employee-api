package com.ukeess.dao.impl;

import com.ukeess.dao.BaseDAO;
import com.ukeess.entity.Department;
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
        return Optional.ofNullable(
                getNamedParameterJdbcTemplate().queryForObject(
                        "SELECT dpID, dpName FROM tblDepartments WHERE dpID=:id",
                        getIdParameterSource(id),
                        getEntityRowMapper())
        );
    }

    public List<Department> getAll() {
        return getNamedParameterJdbcTemplate()
                .query("SELECT dpID, dpName FROM tblDepartments", getEntityRowMapper());
    }

    private boolean exists(int id) {
        return getNamedParameterJdbcTemplate()
                .queryForObject("SELECT COUNT(*) FROM tblDepartments WHERE dpID=:id",
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
