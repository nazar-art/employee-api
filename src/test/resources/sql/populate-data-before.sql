-- delete from tblEmployees;
-- delete
-- from tblDepartments;

truncate table tblDepartments;
truncate table tblEmployees;
truncate table tblUsers;

-- ------------------------------
-- Populate tblUsers table
-- ------------------------------
INSERT INTO tblUsers (userName, userPass, userActive, userRole)
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
VALUES ('Hagrid', true, now(), now(), 1),
       ('Luna', false, now(), now(), 2),
       ('Robert', false, now(), now(), 3);

