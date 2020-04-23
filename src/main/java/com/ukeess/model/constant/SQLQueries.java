package com.ukeess.model.constant;

import lombok.experimental.UtilityClass;

/**
 * @author Nazar Lelyak.
 */
@UtilityClass
public class SQLQueries {

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

    public final String EMPLOYEE_INSERT = "INSERT INTO tblEmployees (empName, empActive, emp_dpID) " +
            "VALUES(:name, :active, :departmentId)";

    public final String EMPLOYEE_UPDATE = "UPDATE tblEmployees " +
            "SET empName = :name, empActive = :active, emp_dpID = :departmentId " +
            "WHERE empID = :id";

    public final String EMPLOYEE_SELECT_ALL = "SELECT empID, empName, empActive, dpID, dpName " +
            "FROM tblEmployees, tblDepartments " +
            "WHERE empID=dpID " +
            "ORDER BY empID";

    public final String EMPLOYEE_SELECT_BY_ID = "SELECT * " +
            "FROM tblEmployees " +
            "LEFT JOIN tblDepartments ON emp_dpID = dpID " +
            "WHERE empID=:id";

//    public final String EMPLOYEES_NAME_STARTS_WITH = "SELECT empID, empName, empActive, dpID, dpName " +
//            "FROM (SELECT empID, empName, empActive, emp_dpID FROM tblEmployees WHERE empName LIKE ?) emplData " +
//            "LEFT OUTER JOIN tblDepartments ON emp_dpID = dpID " +
//            "ORDER BY empID";
    public final String EMPLOYEES_NAME_STARTS_WITH = "SELECT * " +
                                                     "FROM tblEmployees " +
                                                     "LEFT JOIN tblDepartments ON emp_dpID = dpID " +
                                                     "WHERE empName LIKE ?";
    
}
