DROP TABLE IF EXISTS tblEmployees;
DROP TABLE IF EXISTS tblDepartments;
DROP TABLE IF EXISTS tblUsers;

CREATE TABLE IF NOT EXISTS tblDepartments
(
    dpID   SERIAL,
    dpName VARCHAR(25) NOT NULL,
    PRIMARY KEY (dpID)
);


CREATE TABLE IF NOT EXISTS tblEmployees
(
    empID     SERIAL,
    empName   VARCHAR(30)     NOT NULL,
    empActive BIT(1)          NOT NULL,
    emp_dpID  BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (empID),
    FOREIGN KEY (emp_dpID) REFERENCES tblDepartments (dpID)
);

-- CREATE tblUsers table - for jwt authorisation
CREATE TABLE IF NOT EXISTS tblUsers
(
    id       SERIAL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    active   BIT(1)      NOT NULL,
    role     VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);