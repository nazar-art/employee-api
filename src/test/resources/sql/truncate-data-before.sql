-- delete from tblUsers;
-- delete from tblEmployees;
-- delete from tblDepartments;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE tblDepartments;
TRUNCATE TABLE tblEmployees;
TRUNCATE TABLE tblUsers;
SET FOREIGN_KEY_CHECKS = 1;