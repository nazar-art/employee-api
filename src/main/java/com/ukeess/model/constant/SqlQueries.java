package com.ukeess.model.constant;

import lombok.experimental.UtilityClass;

/**
 * @author Nazar Lelyak.
 */
@UtilityClass
public class SqlQueries {

    /**
     * tblDepartments
     */
    public final String DEPARTMENT_SELECT_ALL = "SELECT * FROM tblDepartments";
    public final String DEPARTMENT_SELECT_BY_ID = "SELECT * FROM tblDepartments WHERE dpID=:id";
    public final String DEPARTMENT_TOTAL_COUNT_BY_ID = "SELECT COUNT(*) FROM tblDepartments WHERE dpID=:id";

    /**
     * tblEmployees
     */
    public final String EMPLOYEE_TOTAL_COUNT_BY_ID = "SELECT COUNT(*) FROM tblEmployees WHERE empID=:id";

    public final String EMPLOYEE_DELETE_BY_ID = "DELETE FROM tblEmployees WHERE empID=:id";

    public final String EMPLOYEES_TABLE_TOTAL_COUNT = "select count(*) from tblEmployees";

    public final String EMPLOYEE_INSERT =   "INSERT INTO tblEmployees (empName, empActive, emp_dpID) " +
                                            "VALUES(:name, :active, :departmentId)";

    public final String EMPLOYEE_UPDATE =   "UPDATE tblEmployees " +
                                            "SET empName = :name, empActive = :active, emp_dpID = :departmentId " +
                                            "WHERE empID = :id";

    public final String EMPLOYEE_SELECT_ALL_PAGEABLE =  "SELECT empID, empName, empActive, dpID, dpName " +
                                                        "FROM tblEmployees, tblDepartments " +
                                                        "WHERE empID=dpID " +
                                                        "ORDER BY empID " +
                                                        "LIMIT %s OFFSET %s";

    public final String EMPLOYEE_SELECT_BY_ID = "SELECT * " +
                                                "FROM tblEmployees " +
                                                "LEFT JOIN tblDepartments ON emp_dpID = dpID " +
                                                "WHERE empID=:id";

    public final String EMPLOYEES_NAME_STARTS_WITH_PAGEABLE =   "SELECT * " +
                                                                "FROM tblEmployees " +
                                                                "LEFT JOIN tblDepartments ON emp_dpID = dpID " +
                                                                "WHERE empName LIKE ? " +
                                                                "ORDER BY empID " +
                                                                "LIMIT %s OFFSET %s";
}
