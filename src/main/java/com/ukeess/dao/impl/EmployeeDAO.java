package com.ukeess.dao.impl;

import com.ukeess.dao.GenericDAO;
import com.ukeess.entity.Department;
import com.ukeess.entity.Employee;
import com.ukeess.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.ukeess.model.constant.SQLStatements.EMPLOYEE_DELETE_BY_ID;
import static com.ukeess.model.constant.SQLStatements.EMPLOYEE_INSERT;
import static com.ukeess.model.constant.SQLStatements.EMPLOYEE_NAME_STARTS_WITH;
import static com.ukeess.model.constant.SQLStatements.EMPLOYEE_SELECT_ALL;
import static com.ukeess.model.constant.SQLStatements.EMPLOYEE_SELECT_BY_ID;
import static com.ukeess.model.constant.SQLStatements.EMPLOYEE_TOTAL_COUNT_BY_ID;
import static com.ukeess.model.constant.SQLStatements.EMPLOYEE_UPDATE;

/**
 * @author Nazar Lelyak.
 */
@Repository
public class EmployeeDAO extends NamedParameterJdbcDaoSupport implements GenericDAO<Employee> {

    @Autowired
    public void setJt(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    private RowMapper<Employee> getEntityRowMapper() {
        return (rs, rowNum) -> {
            Department department = Department.builder()
                    .id(rs.getInt("dpID"))
                    .name(rs.getString("dpName"))
                    .build();
            return Employee.builder()
                    .id(rs.getInt("empID"))
                    .name(rs.getString("empName"))
                    .active(rs.getBoolean("empActive"))
                    .department(department)
                    .build();
        };
    }

    private Map<String, ?> getParameterSourceMap(Employee emp, boolean isUpdate) {
        return isUpdate
                ? Map.of("name", emp.getName(),
                "active", emp.getActive(),
                "departmentId", emp.getDepartment().getId(),
                "id", emp.getId())

                : Map.of("name", emp.getName(),
                "active", emp.getActive(),
                "departmentId", emp.getDepartment().getId());
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            return insert(employee);
        } else {
            update(employee);
        }
        return employee;
    }

    private Employee insert(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        getNamedParameterJdbcTemplate().update(
                EMPLOYEE_INSERT,
                new MapSqlParameterSource(getParameterSourceMap(employee, false)),
                keyHolder);

        employee.setId(keyHolder.getKey().intValue());
        return employee;
    }

    private void update(Employee employee) {
        getNamedParameterJdbcTemplate().update(
                EMPLOYEE_UPDATE,
                getParameterSourceMap(employee, true)
        );
    }

    @Override
    public Optional<Employee> getById(int id) {
        if (exists(id)) {
            return Optional.ofNullable(
                    getNamedParameterJdbcTemplate()
                            .queryForObject(EMPLOYEE_SELECT_BY_ID,
                                    getIdParameterSource(id),
                                    getEntityRowMapper())
            );
        }
        throw new EntityNotFoundException(id);
    }

    @Override
    public List<Employee> getAll() {
        return getNamedParameterJdbcTemplate().query(EMPLOYEE_SELECT_ALL, getEntityRowMapper());
    }

    @Override
    public void deleteById(int id) {
        getNamedParameterJdbcTemplate().update(EMPLOYEE_DELETE_BY_ID, getIdParameterSource(id));
    }

    public List<Employee> searchByNameStartWith(String name) {
        return getJdbcTemplate().query(EMPLOYEE_NAME_STARTS_WITH, new String[]{name + "%"}, getEntityRowMapper());
    }

    private boolean exists(int id) {
        return getNamedParameterJdbcTemplate()
                .queryForObject(EMPLOYEE_TOTAL_COUNT_BY_ID, getIdParameterSource(id), Integer.class) > 0;
    }
}
