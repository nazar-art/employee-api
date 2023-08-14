-- delete from tblUsers;
-- delete from tblEmployees;
-- delete from tblDepartments;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE tblDepartments;
TRUNCATE TABLE tblEmployees;
TRUNCATE TABLE tblUsers;
SET FOREIGN_KEY_CHECKS = 1;

-- ------------------------------
-- Populate tblUsers table
-- ------------------------------
INSERT INTO tblUsers(usrName, usrPass, usrActive, usrRole)
VALUES ('harry', 'potter', true, 'ADMIN');

-- ------------------------------
-- Populate tblDepartments table
-- ------------------------------
INSERT INTO tblDepartments (dpName)
VALUES ('Gryffindor'),
       ('Hufflepuff'),
       ('Ravenclaw'),
       ('Slytherin');

-- ------------------------------
-- Populate tblEmployees table
-- ------------------------------
INSERT INTO tblEmployees (empName, empActive, empCreatedAt, empUpdatedAt, emp_dpID)
VALUES ('Hagrid',   true,   now(), now(), 1),
       ('Luna',     false,  now(), now(), 2),
       ('Robert',   false,  now(), now(), 3);

