DROP TABLE IF EXISTS tblUsers;
DROP TABLE IF EXISTS tblEmployees;
DROP TABLE IF EXISTS tblDepartments;

CREATE TABLE IF NOT EXISTS tblDepartments
(
    dpID   SERIAL,
    dpName VARCHAR(10) NOT NULL UNIQUE,
    PRIMARY KEY (dpID)
);

CREATE TABLE IF NOT EXISTS tblEmployees
(
    empID     SERIAL,
    empName   VARCHAR(20)     NOT NULL UNIQUE,
    empActive BIT(1)          NOT NULL,
    emp_dpID  BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (empID),
    FOREIGN KEY (emp_dpID) REFERENCES tblDepartments (dpID)
);

-- CREATE tblUsers table - for authorisation users
CREATE TABLE IF NOT EXISTS tblUsers
(
    userID       SERIAL,
    userName     VARCHAR(10) NOT NULL UNIQUE,
    userPassword VARCHAR(10) NOT NULL,
    userActive   BIT(1)      NOT NULL,
    userRole     VARCHAR(10) NOT NULL,
    PRIMARY KEY (userID)
);

CREATE UNIQUE INDEX ix_username ON tblUsers (userName);