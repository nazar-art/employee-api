package com.ukeess.dao.impl;

import com.ukeess.dao.BaseDAO;
import com.ukeess.dao.GenericDAO;
import com.ukeess.entity.impl.Department;
import com.ukeess.model.dto.TableData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@Repository
public class DepartmentDAO extends BaseDAO<Department> implements GenericDAO<Department> {

    private static final TableData dpTable = TableData.builder()
            .tableName("tblDepartments")
            .id("dpID")
            .name("dpName")
            .field("dpName")
            .build();

    public DepartmentDAO() {
        super(dpTable);
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
