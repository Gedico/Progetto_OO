--Creo la tabella Corriere
CREATE TABLE Corriere
(
	Matricola Varchar(10) primary key,
	Nome Varchar(50) NOT NULL,
	Cognome Varchar(50) NOT NULL
);

--Gestisco i vincoli di corriere 
ALTER TABLE  Corriere
--Controllo Matricola
ADD CONSTRAINT Controllo_Matricola CHECK (Controllo_CoDfis(Matricola)),
--Controllo Nome 
ADD CONSTRAINT Controllo_Nome CHECK (Controllo_Nome(nome)),
--Controllo Cognome
ADD CONSTRAINT Controllo_Cognome CHECK (Controllo_Cognome(cognome));