package com.ukeess.dao.impl;

import com.ukeess.dao.BaseDAO;
import com.ukeess.entity.Department;
import com.ukeess.entity.Employee;
import com.ukeess.exception.EntityNotFoundException;
import com.ukeess.model.constant.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

/**
 * @author Nazar Lelyak.
 */
@Repository
public class EmployeeDAO extends NamedParameterJdbcDaoSupport implements BaseDAO<Employee> {

    @Autowired
    public void setJt(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    private RowMapper<Employee> getEmployeeRowMapper() {
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

    private Map<String, ?> getParameterSourceMap(Employee emp, boolean ifUpdate) {
        return ifUpdate
                ? Map.of("name", emp.getName(),
                "active", emp.getActive(),
                "departmentId", emp.getDepartment().getId(),
                "id", emp.getId())

                : Map.of("name", emp.getName(),
                "active", emp.getActive(),
                "departmentId", emp.getDepartment().getId());
    }

    private boolean exists(int id) {
        return getNamedParameterJdbcTemplate()
                .queryForObject(SQLQuery.EMPLOYEE_TOTAL_COUNT_BY_ID, getIdParameterSource(id), Integer.class) > 0;
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
                SQLQuery.EMPLOYEE_INSERT,
                new MapSqlParameterSource(getParameterSourceMap(employee, false)),
                keyHolder);

        employee.setId(keyHolder.getKey().intValue());
        return employee;
    }

    private void update(Employee employee) {
        getNamedParameterJdbcTemplate().update(
                SQLQuery.EMPLOYEE_UPDATE,
                getParameterSourceMap(employee, true)
        );
    }

    @Override
    public Optional<Employee> getById(int id) {
        if (exists(id)) {
            return Optional.ofNullable(
                    getNamedParameterJdbcTemplate()
                            .queryForObject(SQLQuery.EMPLOYEE_SELECT_BY_ID,
                                    getIdParameterSource(id),
                                    getEmployeeRowMapper())
            );
        }
        throw new EntityNotFoundException(id);
    }

    @Override
    public void deleteById(int id) {
        getNamedParameterJdbcTemplate().update(SQLQuery.EMPLOYEE_DELETE_BY_ID, getIdParameterSource(id));
    }

    @Override
    public Page<Employee> getAll(Pageable pageable) {
        return getEmployeesPage("", SQLQuery.EMPLOYEE_SELECT_ALL_PAGEABLE, pageable);
    }

    public Page<Employee> findAllEmployeesWithNameStartsWith(String nameSnippet, Pageable pageable) {
        return getEmployeesPage(nameSnippet, SQLQuery.EMPLOYEES_NAME_STARTS_WITH_PAGEABLE, pageable);
    }


    private Page<Employee> getEmployeesPage(String nameSnippet, String query, Pageable pageable) {

        String[] args = (nameSnippet.isEmpty()) ? new String[]{} : new String[]{nameSnippet + "%"};

        List<Employee> employees = getJdbcTemplate()
                .query(String.format(query,
                        pageable.getPageSize(), pageable.getOffset()),
                        args,
                        getEmployeeRowMapper());

        return new PageImpl<>(employees, pageable, getTotalRows());
    }

    private long getTotalRows() {
        return getJdbcTemplate().queryForObject(SQLQuery.EMPLOYEES_TABLE_TOTAL_COUNT, long.class);
    }
}
