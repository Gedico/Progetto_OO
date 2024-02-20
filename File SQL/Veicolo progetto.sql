--Creo la tabella Veicolo
CREATE TABLE Veicoli
(
Targa varchar(7) primary key,
Max_Trasportabile int ,
Descrizione Varchar,
Matricola Varchar,
Id_Magazzino INT
);

--Gestisco i vincoli
ALTER TABLE Veicoli
--Controllo  Max_Trasportabile
ADD CONSTRAINT Controllo_Max_Trasportabile CHECK (Max_Trasportabile >= 0),
--Creazione foreign key Matricola
ADD CONSTRAINT FK_Matricola FOREIGN KEY (Matricola)
	REFERENCES Corriere(Matricola) ON DELETE CASCADE,
--Gestisco foreign key Id_Magazzino
ADD CONSTRAINT FK_Id_Magazzino FOREIGN KEY (Id_Magazzino)
REFERENCES Magazzino(Id_Magazzino) ON DELETE CASCADE;