DROP TRIGGER IF EXISTS beforeDeleteOnNauczyciele;
DELIMITER $$
CREATE TRIGGER beforeDeleteOnNauczyciele BEFORE DELETE ON Nauczyciele FOR EACH ROW
BEGIN
    
    -- Updating table 'przedmioty'.
    UPDATE Kursy
    SET Wychowawca = "Brak"
    WHERE Wychowawca = OLD.id;
    
    -- Deleting usuer from tabler 'uzytkownicy'.
    CALL deleteUserTeacher(OLD.id);
    
    
    
END $$
DELIMITER ;

DROP TRIGGER IF EXISTS afterInsertNAuczyciele;
DELIMITER $$
CREATE TRIGGER afterInsertNAuczyciele AFTER INSERT ON Nauczyciele FOR EACH ROW
BEGIN
    
INSERT INTO opinia_nauczyciela (id_nauczyciela) Values (new.id);
END $$
DELIMITER ;
DROP TRIGGER IF EXISTS afterDeleteOnKlas;
DELIMITER $$
CREATE TRIGGER afterDeleteOnKlas AFTER DELETE ON klasa FOR EACH ROW
BEGIN
	DELETE FROM Kurs
    WHERE id = OLD.id_kursu;
    DELETE FROM Zapis_Kurs
    WHERE id_kursu = OLD.id_kursu;
END $$
DELIMITER ;

DROP TRIGGER IF EXISTS afterDeleteOnUczniowie;
DELIMITER $$
CREATE TRIGGER afterDeleteOnUczniowie AFTER DELETE ON Uczniowie FOR EACH ROW
BEGIN
	DELETE FROM Zapis_Kurs
    WHERE id_ucznia = OLD.id;
    
END $$
DELIMITER ;

DROP TRIGGER IF EXISTS makeCourse;
DELIMITER $$
CREATE TRIGGER makeCourse After Insert ON Zapis_Kurs FOR EACH ROW
BEGIN

	SET @zapis = (SELECT liczba_zapisanych FROM Kursy Where id=new.id_kursu);
    SET @miejsa =(SELECT liczba_miejsc FROM Kursy Where id=new.id_kursu);
     IF (@zapis<@miejsa) THEN
	UPDATE Kursy
    SET liczba_zapisanych  = liczba_zapisanych+ 1 
    WHERE id = new.id_kursu;
    END IF;
	SET @zapis =(SELECT liczba_zapisanych FROM Kursy Where id=new.id_kursu);
    IF (@zapis=@miejsa) THEN
    UPDATE Kursy
    SET status_kursu =true 
    WHERE id = new.id_kursu;

    Insert Into Klasa Values(
    new.id_kursu,
    (SELECT     Wychowawca FROm Kursy WHERE id=new.id_kursu), 
    (SELECT Nazwa FROM Języki WHERE id=(Select id_języka FROM Kursy Where id=new.id_kursu) ),
	(SELECT  Poziom FROM Języki WHERE id=(Select id_języka FROM Kursy Where id=new.id_kursu) ),
    ELT(FLOOR(RAND()*7 + 1), 'Poniedziałek','Wtorek','Środa','Czwartek','Piątek','Sobota','Niedziela'),
	ELT(FLOOR(RAND()*7 + 1), '160000','170000','180000','190000','200000','203000','173000')
);
    END IF;

    
END $$
DELIMITER ;

DROP TRIGGER IF EXISTS leaveCourse;
DELIMITER $$
CREATE TRIGGER leaveCourse After DELETE ON Zapis_Kurs FOR EACH ROW
BEGIN
    
	UPDATE Kursy
    SET liczba_zapisanych  = liczba_zapisanych - 1 
    WHERE id = OLD.id_kursu;
END $$
DELIMITER ;

DROP TRIGGER IF EXISTS afterDeletestudent;
DELIMITER $$
CREATE TRIGGER afterDeletestudent After Delete ON Zapis_Kurs FOR EACH ROW
BEGIN
	UPDATE Kursy
    SET liczba_zapisanych  = liczba_zapisanych - 1 
    WHERE id = OLD.id_kursu;
    
END $$
DELIMITER ;

DROP TRIGGER IF EXISTS makeOpinion;
DELIMITER $$
CREATE TRIGGER makeOpinion After Insert ON Opinia FOR EACH ROW
BEGIN
		UPDATE Opinia_nauczyciela
        SET Opinia = (SELECT AVG(Ocena) FROM Opinia WHERE id_nauczyciela=new.id_nauczyciela Group by id_nauczyciela)
		WHERE id_nauczyciela = new.id_nauczyciela;
        END $$
DELIMITER ;
DROP TRIGGER IF EXISTS beforeMakeOpinion;
DELIMITER $$
CREATE TRIGGER beforeMakeOpinion Before Insert ON Opinia FOR EACH ROW
BEGIN
		If(new.Ocena<0 ||new.Ocena>10) Then
              SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ocena od 1 do 10!';
    end if;
        END $$
DELIMITER ;


