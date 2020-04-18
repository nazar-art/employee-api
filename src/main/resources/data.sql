INSERT INTO ukeess_db.tbl_departments (dp_name) VALUES ('Gryffindor');
INSERT INTO ukeess_db.tbl_departments (dp_name) VALUES ('Hufflepuff');
INSERT INTO ukeess_db.tbl_departments (dp_name) VALUES ('Ravenclaw');
INSERT INTO ukeess_db.tbl_departments (dp_name) VALUES ('Slytherin');


INSERT INTO ukeess_db.tbl_employees (emp_name, emp_active, emp_dpid)
VALUES ('Garry', true, 1);

INSERT INTO ukeess_db.tbl_employees (emp_name, emp_active, emp_dpid)
VALUES ('Ron', true, 1);

INSERT INTO ukeess_db.tbl_employees (emp_name, emp_active, emp_dpid)
VALUES ('Hermiona', true, 1);

INSERT INTO ukeess_db.tbl_employees (emp_name, emp_active, emp_dpid)
VALUES ('Hagrid', true, 1);

INSERT INTO ukeess_db.tbl_employees (emp_name, emp_active, emp_dpid)
VALUES ('Luna', false, 2);

INSERT INTO ukeess_db.tbl_employees (emp_name, emp_active, emp_dpid)
VALUES ('Robert', false , 3);

INSERT INTO ukeess_db.tbl_employees (emp_name, emp_active, emp_dpid)
VALUES ('Draco', true, 4);