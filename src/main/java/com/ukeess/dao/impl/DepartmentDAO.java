package com.ukeess.dao.impl;

import com.google.common.collect.Lists;
import com.ukeess.dao.BaseDAO;
import com.ukeess.dao.GenericDAO;
import com.ukeess.entity.impl.Department;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@Repository
public class DepartmentDAO extends BaseDAO<Department> implements GenericDAO<Department> {

    public static final String DEPARTMENTS_TABLE_NAME = "tblDepartments";
    public static final String DEPARTMENTS_ID_NAME = "dpID";
    public static final List<String> departmentsFields = Lists.newArrayList("dpName");

    public DepartmentDAO() {
        super(DEPARTMENTS_TABLE_NAME, DEPARTMENTS_ID_NAME, departmentsFields);
    }

    @Override
    protected RowMapper<Department> getRowMapper() {
        return (rs, rowNum) -> Department.builder()
                .id(rs.getInt("dpID"))
                .name(rs.getString("dpName"))
                .build();
    }

    public List<Department> getAll() {
        return getNamedParameterJdbcTemplate()
                .query("SELECT dpID, dpName FROM tblDepartments", getRowMapper());
    }
}
