DROP TABLE IF EXISTS tblEmployees;
DROP TABLE IF EXISTS tblDepartments;

CREATE TABLE IF NOT EXISTS tblDepartments
(
    dpID   SERIAL,
    dpName VARCHAR(20) NOT NULL,
    PRIMARY KEY (dpID)
);


CREATE TABLE IF NOT EXISTS tblEmployees
(
    empID     SERIAL,
    empName   VARCHAR(20)     NOT NULL,
    empActive BIT(1)      NOT NULL,
    emp_dpID  BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (empID),
    FOREIGN KEY (emp_dpID) REFERENCES tblDepartments (dpID)
);