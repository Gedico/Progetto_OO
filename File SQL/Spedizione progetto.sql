--Creazione tabella Spedizione
CREATE TABLE Spedizione
(
	ID_Spedizione INT PRIMARY KEY,
	Targa VARCHAR(7) NOT NULL,
	ID_Operatore INT NOT NULL,
	Peso_Totale INT NOT NULL,
	Data_Spedizione DATE NOT NULL
);

--Creazione dei vincoli 
ALTER TABLE Spedizione
--Creo foreign key targa
ADD CONSTRAINT FK_Targa FOREIGN KEY (Targa)
    REFERENCES VeicolI(Targa) ON DELETE CASCADE,
--Creo la foreign key per Operatore 
ADD CONSTRAINT FK_Operatore FOREIGN KEY (ID_Operatore)
    REFERENCES Operatore(ID_Operatore) ON DELETE CASCADE,
--Controllo su peso
ADD CONSTRAINT Controllo_PesoTotale CHECK (peso_Totale > 0),
--Controllo su data 
ADD CONSTRAINT Controllo_Data_Spedizione CHECK (Data_Spedizione = current_date);


--Gestisco l'eventualità che la chiave inserita sia nulla , autosmistamento.
CREATE OR REPLACE FUNCTION KeyNullaSpedizione ()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.ID_Spedizione IS NULL THEN
        SELECT MAX(ID_Spedizione) + 1 INTO NEW.ID_Spedizione FROM Spedizione;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER GestioneSpedizione
BEFORE INSERT ON Spedizione
FOR EACH ROW
EXECUTE FUNCTION KeyNullaSpedizione();