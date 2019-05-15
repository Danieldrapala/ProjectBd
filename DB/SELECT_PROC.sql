DELIMITER $$
DROP PROCEDURE IF EXISTS selectAllStudents$$
CREATE PROCEDURE selectAllStudents ()
BEGIN
		SET @query = CONCAT("SELECT * FROM Uczniowie");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectAllTeachers$$
CREATE PROCEDURE selectAllTeachers ()
BEGIN
		SET @query = CONCAT("SELECT * FROM Nauczyciele;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectStudentsReduced$$
CREATE PROCEDURE selectStudentsReduced ()
BEGIN
		SET @query = CONCAT("SELECT imię, nazwisko, K.id AS 'Kod kursu' FROM Uczniowie U JOIN Zapis_Kurs ZK ON ZK.id_ucznia=U.id JOIN Kursy K ON ZK.id_kursu=K.id;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectOpenCourses$$
CREATE PROCEDURE selectOpenCourses (
    )
BEGIN
		SET @query = CONCAT("SELECT K.id AS 'kod kursu', J.Nazwa AS 'jezyk', J.Poziom As 'Poziom' ,K.liczba_miejsc ,K.liczba_zapisanych As 'zajęte miejsca' FROM Kursy K JOIN Języki J ON J.id=K.id_języka WHERE K.Status_kursu=false;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;
DELIMITER $$
DROP PROCEDURE IF EXISTS selectClass$$
CREATE PROCEDURE selectClass (
	IN id INT
    )
BEGIN
		SET @query = CONCAT("SELECT imię AS 'Imię',  nazwisko AS 'Nazwisko' FROM Uczniowie U JOIN Zapis_Kurs ZK ON U.id=ZK.id_ucznia JOIN Klasa K ON K.id_kursu=ZK.id_kursu WHERE K.id_kursu='",id,"';");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;


DELIMITER $$
DROP PROCEDURE IF EXISTS selectClassLanguageLevel$$
CREATE PROCEDURE selectClassLanguageLevel (
	IN id INT
    )
BEGIN
		SET @query = CONCAT("SELECT K.id_kursu AS 'Kod kursu', K.Nazwa As 'Język' K.Poziom As 'Poziom' FROM Klasa K WHERE K.id_kursu='",id,"';");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectTeachers$$
CREATE PROCEDURE selectTeachers ()
BEGIN
		SET @query = CONCAT("SELECT * FROM Nauczyciele ;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;





DELIMITER $$
DROP PROCEDURE IF EXISTS selectTeacherInfo$$
CREATE PROCEDURE selectTeacherInfo (
	IN id INT
)
BEGIN
		SET @query = CONCAT("SELECT N.imię AS 'Imię', N.nazwisko AS 'Nazwisko',SUM(K.id_kursu) AS 'Liczba Klas prowadzonych'  FROM Nauczyciele N JOIN Klasa K ON K.Wychowawca = N.id WHERE id = '",id,"' GROUP BY K.Wychowawca;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @query = CONCAT("SELECT Distinct K.Poziom AS 'Poziom', K.nazwa AS 'Język', FROM  Klasa K JOIN Nauczyciele N  ON N.id=K.Wychowawca  WHERE N.id = '",id,"';");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;



DELIMITER $$
DROP PROCEDURE IF EXISTS selectUsersStudents$$
CREATE PROCEDURE selectUsersStudents()
BEGIN
		SET @query = CONCAT("SELECT * FROM UsersStudents;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;


DELIMITER $$
DROP PROCEDURE IF EXISTS selectUsersTeachers$$
CREATE PROCEDURE selectUsersTeachers ()
BEGIN
		SET @query = CONCAT("SELECT * FROM UsersTeachers;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;


