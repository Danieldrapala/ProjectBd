DELIMITER $$
DROP PROCEDURE IF EXISTS createUser$$
CREATE PROCEDURE createUser (
    IN usrname VARCHAR(10),
    IN usrpass VARCHAR(20),
    IN usrcode VARCHAR(6),
    IN usrprivileges VARCHAR(15))
BEGIN
	
    SET @query = CONCAT("CREATE USER '",usrname,"'@'localhost';");
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	
    SET @query = CONCAT("ALTER USER '",usrname,"'@'localhost' IDENTIFIED BY '",usrpass,"';");
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    
    IF (usrprivileges = 'administrator') THEN 
    SET @query = CONCAT("GRANT administrator TO '", usrname ,"'@'localhost';");
    ELSEIF (usrprivileges = 'uczen') THEN
    SET @query = CONCAT("GRANT uczen TO '", usrname ,"'@'localhost';");
    ELSEIF (usrprivileges = 'nauczyciel') THEN
    SET @query = CONCAT("GRANT nauczyciel TO '", usrname ,"'@'localhost';");
    END IF;
    
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    
	SET @query = CONCAT("SET DEFAULT ROLE ",usrprivileges," to '",usrname,"'@'localhost';");
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;  
    
     SET @query = CONCAT("FLUSH PRIVILEGES;");
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;   
    
	IF (usrprivileges = 'administrator') THEN 
	SET @query = CONCAT("INSERT INTO Admins VALUES('",usrname,"','",usrprivileges,"');");    
	ELSEIF (usrprivileges = 'uczen') THEN
	SET @query = CONCAT("INSERT INTO UsersStudents VALUES('",usrname,"','",usrcode,"','",usrprivileges,"');");    
    ELSEIF (usrprivileges = 'nauczyciel') THEN
	SET @query = CONCAT("INSERT INTO UsersTechears VALUES('",usrname,"','",usrcode,"','",usrprivileges,"');");    
    END IF;
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;   
    
END $$
DELIMITER ;


-- delete usr, modify usr
DELIMITER $$
DROP PROCEDURE IF EXISTS modifyUser$$
CREATE PROCEDURE modifyUser (
    IN usrlogin VARCHAR(10),
    IN coltochange VARCHAR(20),
    IN newval VARCHAR(20),
    IN usertype VARCHAR(10)
    )
BEGIN
	IF (coltochange = 'LOGIN') THEN
		SET @query = CONCAT("RENAME USER '",usrlogin,"'@'localhost' TO '", newval ,"'@'localhost';");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        	IF (usertype = 'administrator') THEN 
        SET @query = CONCAT("UPDATE Admins SET LOGIN='",newval,"' WHERE LOGIN='",usrlogin,"';");
	ELSEIF (usertype = 'uczen') THEN
        SET @query = CONCAT("UPDATE UsersStudents SET LOGIN='",newval,"' WHERE LOGIN='",usrlogin,"';");
    ELSEIF (usertype = 'nauczyciel') THEN
        SET @query = CONCAT("UPDATE UsersTeachers SET LOGIN='",newval,"' WHERE LOGIN='",usrlogin,"';");
    END IF;
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
	
	ELSEIF(coltochange = 'Haslo') THEN
		SET @query = CONCAT("ALTER USER '",usrlogin,"'@'localhost' IDENTIFIED BY '",newval,"';");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
	END IF;
    
        
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS deleteUser$$
CREATE PROCEDURE deleteUser (
    IN usrname VARCHAR(10),
	IN usertype VARCHAR(10)

)
BEGIN
		SET @query = CONCAT("DROP USER '",usrname,"'@'localhost';");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        IF (usertype = 'administrator') THEN 
        SET @query = CONCAT("DELETE FROM Admins WHERE LOGIN='",usrname,"';");
	ELSEIF (usertype = 'uczen') THEN
        SET @query = CONCAT("DELETE FROM UsersStudents WHERE LOGIN='",usrname,"';");
    ELSEIF (usertype = 'nauczyciel') THEN
        SET @query = CONCAT("DELETE FROM UsersTeachers WHERE LOGIN='",usrname,"';");
    END IF;
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS checkAdminPrivileges$$
CREATE PROCEDURE checkAdminPrivileges (
    IN usrname VARCHAR(10)
    )

BEGIN
          
        SET @query = CONCAT("SELECT uprawnienia FROM Admins WHERE LOGIN = '",usrname,"';");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS checkTeacherPrivileges$$
CREATE PROCEDURE checkTeacherPrivileges (
    IN usrname VARCHAR(10)
    )

BEGIN
          
        SET @query = CONCAT("SELECT uprawnienia FROM UsersTeachers WHERE LOGIN = '",usrname,"';");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS checkStudentPrivileges$$
CREATE PROCEDURE checkStudentPrivileges (
    IN usrname VARCHAR(10)
    )

BEGIN
          
        SET @query = CONCAT("SELECT uprawnienia FROM UsersStudents WHERE LOGIN = '",usrname,"';");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;