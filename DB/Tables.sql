CREATE TABLE IF NOT EXISTS Nauczyciele(
	id INT NOT NULL AUTO_INCREMENT,
	PESEL CHAR(11) NOT NULL,
	Imię VARCHAR(20) NOT NULL,
	Nazwisko VARCHAR(30) NOT NULL,
    Data_urodzenia DATE NOT NULL,
	Adres VARCHAR(30) NOT NULL,
	Kod_pocztowy CHAR(6) NOT NULL,
	Miasto VARCHAR(20) NOT NULL,
	Email VARCHAR(30),
	Telefon_kontaktowy CHAR(9),
	PRIMARY KEY(id,PESEL),					
	CONSTRAINT validate_negvalues CHECK (PESEL >= 0),
    CONSTRAINT validate_pesel CHECK (
        SUBSTRING(PESEL, 1, 2) = SUBSTRING(YEAR(data_urodzenia), 3, 2) AND
        SUBSTRING(PESEL, 5, 2) = DAY(data_urodzenia) AND
        ((SUBSTRING(PESEL, 3, 2) = MONTH(data_urodzenia) AND (CAST(YEAR(data_urodzenia) AS unsigned) <= 1999))
        OR
        (SUBSTRING(PESEL, 3, 2) = MONTH(data_urodzenia)+20 AND (CAST(YEAR(data_urodzenia) AS unsigned) >= 2000 )))
        )
);

CREATE TABLE IF NOT EXISTS Uczniowie(
	id INT NOT NULL AUTO_INCREMENT ,
    PESEL CHAR(11) NOT NULL,
    Imię VARCHAR(20) NOT NULL,
	Nazwisko VARCHAR(30) NOT NULL,
    Data_urodzenia DATE NOT NULL,
	Adres VARCHAR(30) NOT NULL,
	Kod_pocztowy CHAR(6) NOT NULL,
	Miasto VARCHAR(20) NOT NULL,
    PRIMARY KEY(id,PESEL),
	CONSTRAINT validate_code CHECK(Kod_pocztowy.length()=6),
	CONSTRAINT validate_negvalues CHECK (PESEL >= 0), -- no jak narazie to slaba validacja czy pesel jest ok
	CONSTRAINT validate_pesel CHECK (
        SUBSTRING(PESEL, 1, 2) = SUBSTRING(YEAR(data_urodzenia), 3, 2) AND
        SUBSTRING(PESEL, 5, 2) = DAY(data_urodzenia) AND
        ((SUBSTRING(PESEL, 3, 2) = MONTH(data_urodzenia) AND (CAST(YEAR(data_urodzenia) AS unsigned) <= 1999))
        OR
        (SUBSTRING(PESEL, 3, 2) = MONTH(data_urodzenia)+20 AND (CAST(YEAR(data_urodzenia) AS unsigned) >= 2000 )))
        )
);

CREATE TABLE IF NOT EXISTS UsersTechears(
	LOGIN VARCHAR(15) NOT NULL,
	id_nauczyciela INT NOT NULL,
	Uprawnienia ENUM( 'nauczyciel') NOT NULL,
	PRIMARY KEY (LOGIN),
    Foreign Key (id_nauczyciela)
    References Nauczyciele(id)
);
CREATE TABLE IF NOT EXISTS UsersStudents(
	LOGIN VARCHAR(15) NOT NULL,
    id_ucznia INT NOT NULL,
	Uprawnienia ENUM( 'uczen') NOT NULL,
	PRIMARY KEY (LOGIN),
     Foreign Key (id_ucznia)
    References Uczniowie(id)
);
CREATE TABLE IF NOT EXISTS Admins(
	LOGIN VARCHAR(15) NOT NULL,
	Uprawnienia ENUM( 'administrator') NOT NULL,
	PRIMARY KEY (LOGIN)
    );




CREATE TABLE IF NOT EXISTS Języki(
	Nazwa VARCHAR(30) NOT NULL,
	id INT NOT NULL AUTO_INCREMENT ,
    Poziom VARCHAR(6) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Kursy(
	id INT NOT NULL AUTO_INCREMENT,
	id_języka INT NOT NULL,
    Wychowawca INT NOT NULL,
    Liczba_miejsc INT(2),
    Liczba_zapisanych INT(2),
    Status_kursu Bool,
    PRIMARY KEY(id),
    FOREIGN KEY(Wychowawca)
    REFERENCES Nauczyciele(id),
    FOREIGN KEY(id_języka)
    REFERENCES Języki(id)
);
CREATE TABLE IF NOT EXISTS Klasa(
	
	id_kursu INT NOT NULL,
    Wychowawca  INT NOT NULL,   
    Nazwa VARCHAR(30) NOT NULL,
    Poziom VARCHAR(6) NOT NULL,
    dzień VARCHAR(15),
    godzina time,
    FOREIGN KEY(id_kursu)
    REFERENCES Kursy(id),
    FOREIGN KEY(Wychowawca)
    REFERENCES Nauczyciele(id)
   );

CREATE TABLE IF NOT EXISTS Zapis_Kurs(
	id_kursu INT NOT NULL,
    id_ucznia INT NOT NULL,
    foreign key(id_kursu)
    References Kursy(id),
    foreign key(id_ucznia)
    References Uczniowie(id)
    );
    
    CREATE TABLE IF NOT EXISTS Opinia(
	id INT NOT NULL AUTO_INCREMENT ,
    id_ucznia INT NOT NULL NOT NULL,
    id_nauczyciela INT NOT NULL NOT NULL,
	Ocena INT(2),
    PRIMARY KEY(id),
    foreign key(id_ucznia)
    References Uczniowie(id),
	foreign key(id_nauczyciela)
    References Nauczyciele(id),
	CONSTRAINT validate_negvalues CHECK (Ocena >= 1&&Ocena<=10)

    );
    CREATE TABLE IF NOT EXISTS Opinia_nauczyciela(
    id_nauczyciela INT NOT NULL,
    Opinia float default 0,
    foreign key(id_nauczyciela)
    References Nauczyciele(id)
    );
    

