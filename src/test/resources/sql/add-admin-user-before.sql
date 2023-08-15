SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE tblUsers;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO tblUsers(usrName, usrPass, usrActive, usrRole)
VALUES ('harry', 'potter', true, 'ADMIN');