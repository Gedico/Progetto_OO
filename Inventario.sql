--Creazione tabella Inventario
CREATE TABLE Inventario
(
	ID_Merce INT NOT NULL, 
	ID_Magazzino INT NOT NULL,
	Quantitativo INT --Il quantitativo può essere nullo
);

--Gestisco i vincoli in Inventario
ALTER TABLE Inventario
--Vincolo quantitativo >=0
ADD CONSTRAINT Controllo_Quantitativo CHECK (Quantitativo >= 0),
--Creazione foreign key su ID_Merce
ADD CONSTRAINT FK_ID_Merce FOREIGN KEY (ID_Merce)
	REFERENCES Merce(ID_Merce) ON DELETE CASCADE,

--Creazione foreign key su ID_Magazzino
ADD CONSTRAINT FK_ID_Magazzino FOREIGN KEY (ID_Magazzino)
	REFERENCES Magazzino(ID_Magazzino) ON DELETE CASCADE;--