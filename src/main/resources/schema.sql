DROP TABLE IF EXISTS tblUsers;
DROP TABLE IF EXISTS tblEmployees;
DROP TABLE IF EXISTS tblDepartments;

CREATE TABLE IF NOT EXISTS tblDepartments
(
    dpID   SERIAL,
    dpName VARCHAR(10) NOT NULL,
    PRIMARY KEY (dpID)
);

CREATE TABLE IF NOT EXISTS tblEmployees
(
    empID     SERIAL,
    empName   VARCHAR(20)     NOT NULL,
    empActive BIT(1)          NOT NULL,
    emp_dpID  BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (empID),
    FOREIGN KEY (emp_dpID) REFERENCES tblDepartments (dpID)
);

-- CREATE tblUsers table - for authorisation users
CREATE TABLE IF NOT EXISTS tblUsers
(
    id       SERIAL,
    username VARCHAR(10) NOT NULL,
    password VARCHAR(10) NOT NULL,
    active   BIT(1)      NOT NULL,
    role     VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_username ON tblUsers(username);