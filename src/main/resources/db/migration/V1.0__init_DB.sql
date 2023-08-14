CREATE TABLE tblUsers
(
    usrID     INT AUTO_INCREMENT PRIMARY KEY,
    usrName   VARCHAR(24) NOT NULL,
    usrPass   VARCHAR(64) NOT NULL,
    usrActive BOOLEAN     NOT NULL,
    usrRole   VARCHAR(20) NOT NULL,
    CONSTRAINT tblUsers_usrName_uq UNIQUE INDEX (usrName)
);

CREATE TABLE tblDepartments
(
    dpID   INT AUTO_INCREMENT PRIMARY KEY,
    dpName VARCHAR(24) NOT NULL,
    CONSTRAINT tblDepartments_dpName_uq UNIQUE (dpName)
);

CREATE TABLE tblEmployees
(
    empID        INT AUTO_INCREMENT PRIMARY KEY,
    empName      VARCHAR(24) NOT NULL,
    empActive    BOOLEAN     NOT NULL,
    emp_dpID     INT         NOT NULL,
    empCreatedAt TIMESTAMP DEFAULT now(),
    empUpdatedAt TIMESTAMP DEFAULT now()
);

ALTER TABLE tblEmployees
    ADD CONSTRAINT tblEmployees_tblDepartments_fk FOREIGN KEY (emp_dpID) REFERENCES tblDepartments (dpID) ON DELETE CASCADE ON UPDATE CASCADE;

