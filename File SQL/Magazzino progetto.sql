--Creazione tabella Magazzino 
CREATE TABLE Magazzino
(
	ID_Magazzino INT PRIMARY KEY,
	Città VARCHAR(50) NOT NULL,
	Descrizione VARCHAR(100),
);

--Gestione dei vincoli
ALTER TABLE Magazzino
--Controllo su lettere città
ADD CONSTRAINT Controllo_città CHECK (Controllo_Città(Città)),
--Controllo città unica
ADD CONSTRAINT Solo_Una_Sede UNIQUE (Città),


--Gestisco l'eventualità che la chiave inserita sia nulla , autosmistamento.
CREATE OR REPLACE FUNCTION KeyNullaMagazzino()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.ID_Magazzino IS NULL THEN
        SELECT MAX(ID_Magazzino) + 1 INTO NEW.ID_Magazzino FROM Magazzino;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER GestioneMagazzino
BEFORE INSERT ON Magazzino
FOR EACH ROW
EXECUTE FUNCTION KeyNullaMagazzino();