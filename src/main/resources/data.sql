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
VALUES ('Harry', true, now(), now(), 1),
       ('Ron', true, now(), now(), 1),
       ('Hermione', true, now(), now(), 1),
       ('Hagrid', true, now(), now(), 1),
       ('Luna', false, now(), now(), 2),
       ('Robert', false, now(), now(), 3),
       ('Draco', true, now(), now(), 4),
       ('Voldemort', true, now(), now(), 2),
       ('Snape', true, now(), now(), 1),
       ('Dumbledore', true, now(), now(), 3);


-- ------------------------------
-- Populate tblUsers table
-- ------------------------------
INSERT INTO tblUsers (userName, userPass, userActive, userRole)
VALUES ('harry', 'potter', true, 'ADMIN'),
       ('ron', 'weasley', true, 'ADMIN'),
       ('hermione', 'granger', true, 'USER');
