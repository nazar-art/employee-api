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
VALUES ('Harry', true, 1);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Ron', true, 1);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Hermiona', true, 1);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Hagrid', true, 1);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Luna', false, 2);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Robert', false, 3);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Draco', true, 4);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Voldemort', true, 2);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Snape', true, 1);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Dumbledore', true, 3);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Dobi', true, 2);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Minerva', true, 3);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Lockhart', true, 4);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Filch', true, 2);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Flitwick', true, 3);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('Pomfrey', true, 3);
INSERT INTO tblEmployees (empName, empActive, emp_dpid)
VALUES ('The Bloody Baron', true, 2);
