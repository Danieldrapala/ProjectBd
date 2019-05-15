DELIMITER $$
DROP PROCEDURE IF EXISTS addStudent$$
CREATE PROCEDURE addStudent (
	IN PESEL CHAR(11),
	IN imię VARCHAR(20),
	IN nazwisko VARCHAR(30 ),
    IN data_urodzenia DATE,
    IN adres VARCHAR(30),
	IN kod_pocztowy CHAR(6),
	IN miasto VARCHAR(20))

    BEGIN
		SET @result = CONCAT("INSERT INTO Uczniowie VALUES ('",PESEL,"','", imię,"','",nazwisko,"','",data_urodzenia,"','",adres,"','",
        kod_pocztowy,"','",miasto,"');");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS addTeacher$$
CREATE PROCEDURE addTeacher (
	IN PESEL CHAR(11)  ,
	IN Imię VARCHAR(20)  ,
	IN Nazwisko VARCHAR(30)  ,
    IN Data_urodzenia DATE  ,
	IN Adres VARCHAR(30)  ,
	IN Kod_pocztowy CHAR(6)  ,
	IN Miasto VARCHAR(20)  ,
	IN Email VARCHAR(30),
	IN Telefon_kontaktowy CHAR(9))
BEGIN
		
		
		SET @result = CONCAT("INSERT INTO Nauczyciele VALUES ('",PESEL,"','", imię,"','",nazwisko,"','",data_urodzenia,"','",adres,"','",
        kod_pocztowy,"','",miasto,"','",email,"','",telefon_kontaktowy,"');");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS addLanguage$$
CREATE PROCEDURE addLanguage (
	IN nazwa VARCHAR(30),
    IN poziom VARCHAR(4)) 
BEGIN
		SET @result = CONCAT("INSERT INTO Jezyki VALUES ('",nazwa,"', '",poziom,"');");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;
DELIMITER $$
DROP PROCEDURE IF EXISTS joinCourse$$
CREATE PROCEDURE joinCourse (
	IN  id_kursu INT,
    IN  id_ucznia INt) 
BEGIN
		SET @result = CONCAT("INSERT INTO Zapisz_Kurs VALUES ('",id_kursu,"', '",id_ucznia,"');");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS addCourse$$
CREATE PROCEDURE addCourse (
	In id_języka INT  ,
    In Wychowawca INT)
BEGIN
		
        SET @classnum = FLOOR(RAND()*10+10);
        SET @classsym = SUBSTRING(nazwa,2,3);
		SET @result = CONCAT("INSERT INTO Kursy VALUES ('",id_języka,"',",Wychowawca,", '",@classnum,"', '0','false');");
		PREPARE stmt2 FROM @result;
		EXECUTE stmt2;
		DEALLOCATE PREPARE stmt2;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS modifyStudent$$
CREATE PROCEDURE modifyStudent (
    IN id INT ,
    IN kol VARCHAR(20),
    IN newval VARCHAR(30))
BEGIN
		
        IF (kol = "Data_urodzenia") THEN
			SET @val = CAST(newval AS date);
		ELSE SET @val = newval;
		END IF;
		SET @result = CONCAT("UPDATE Uczniowie SET ",kol,"='",@val,"' WHERE id ='",id,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS modifyTeacher$$
CREATE PROCEDURE modifyTeacher (
    IN id INT,
    IN kol VARCHAR(20),
    IN newval VARCHAR(30))
BEGIN
        IF (kol = "data_urodzenia") THEN
			SET @val = CAST(newval AS DATE);
            SET @result = CONCAT("UPDATE Nauczyciele SET ",kol,"=",@val," WHERE id ='",id,"';");
		ELSE 
			SET @val = newval;
			SET @result = CONCAT("UPDATE Nauczyciele SET ",kol,"='",@val,"' WHERE id ='",id,"';");
		END IF;
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS modifyLanguage$$
CREATE PROCEDURE modifyLanguage (
	IN id INT,
    IN col VARCHAR(30),
    IN newval VARCHAR(30))
BEGIN
        SET @val = newval;
	
		SET @result = CONCAT("UPDATE Języki SET ",col,"='",@val,"' WHERE  id='",id,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS modifyCourse$$
CREATE PROCEDURE modifyCourse (
    IN id INT,
    IN col VARCHAR(30),
    IN newval INT)
BEGIN

		SET @val = newval;
		SET @result = CONCAT("UPDATE Kursy SET ",col,"='",@val,"' WHERE id='",id,"';");

		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS deleteStudent$$
CREATE PROCEDURE deleteStudent (
    IN id INT)
BEGIN
		SET @result = CONCAT("DELETE FROM Uczniowie WHERE id='",id,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS deleteTeacher$$
CREATE PROCEDURE deleteTeacher (
    IN id INT)
BEGIN
		SET @result = CONCAT("DELETE FROM Nauczyciele WHERE id='",id,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS leaveCourse$$
CREATE PROCEDURE leaveCourse (
	IN  id_kursu INT,
    IN  id_ucznia INt) 
BEGIN
		SET @result = CONCAT("DELETE FROM Zapis_Kurs WHERE id_kursu='",id_kursu,"'&& id_ucznia='",id_ucznia,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS deleteCourse$$
CREATE PROCEDURE deleteCourse (
    IN id INT)
BEGIN
		SET @result = CONCAT("DELETE FROM Kursy WHERE id='",id,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS deleteLanguage$$
CREATE PROCEDURE deleteLanguage (
In id INT)
BEGIN
		SET @result = CONCAT("DELETE FROM Języki WHERE id='",id,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS deleteOpinion$$
CREATE PROCEDURE deleteOpinion (
In id INT)
BEGIN
		SET @result = CONCAT("DELETE FROM Opinia WHERE id='",id,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;
DELIMITER $$

DROP PROCEDURE IF EXISTS modifyOpinion$$
CREATE PROCEDURE modifyOpinion(
	IN id_ucznia INT,
    IN id_nauczyciela INT,
    IN newval INT(2))
BEGIN
        SET @val = newval;
	
		SET @result = CONCAT("UPDATE Opinia SET Ocena='",@val,"' WHERE  id_ucznia='",id_ucznia,"'&& id_nauczyciela='",id_nauczyciela,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS addOpinion$$
CREATE PROCEDURE addOpinion (
	IN id_ucznia INT,
	IN id_nauczyciela INT,
    IN Ocena INT(2)) 
BEGIN
		SET @result = CONCAT("INSERT INTO Opinia VALUES ('",id_ucznia,"',",id_nauczyciela,"', '",Ocena,"');");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;