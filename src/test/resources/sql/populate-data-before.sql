-- delete from tblEmployees;
-- delete from tblDepartments;

truncate table tblDepartments;
truncate table tblEmployees;

-- ------------------------------
-- Populate tblDepartments table
-- ------------------------------
INSERT INTO tblDepartments (dpName)
VALUES ('Gryffindor');
INSERT INTO tblDepartments (dpName)
VALUES ('Hufflepuff');
INSERT INTO tblDepartments (dpName)
VALUES ('Ravenclaw');
INSERT INTO tblDepartments (dpName)
VALUES ('Slytherin');

-- ------------------------------
-- Populate tblEmployees table
-- ------------------------------
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Hagrid', true, 1);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Luna', false, 2);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Robert', false, 3);

