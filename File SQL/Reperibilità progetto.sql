--Creo la tabella Reperibilità
CREATE TABLE Reperibilità
(
	Targa varchar(7) NOT NULL,
	Data DATE NOT NULL,
	Disponibilità BOOL DEFAULT FALSE
);


--Gestisco i vincoli per Reperibiluità
ALTER TABLE Reperibilità
--Controllo sulla data 
ADD CONSTRAINT Controllo_Data CHECK (Data >= current_date),
--Creo la foreign key per Targa
ADD CONSTRAINT FK_Targa FOREIGN KEY (Targa)
    REFERENCES VeicolI(Targa) ON DELETE CASCADE;