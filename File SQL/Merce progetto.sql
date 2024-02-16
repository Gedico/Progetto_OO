--Creazione della tabella Merce 
CREATE TABLE Merce
(
	ID_Merce INT PRIMARY KEY,
	Peso INT NOT NULL,
	Descrizione VARCHAR(100)
);

--Gestisco i vincoli per Merce
ALTER TABLE Merce
--Unicità nella descrizione della merce
ADD CONSTRAINT Descrizione_Merce_Unica UNIQUE (Descrizione),
--Vincoli su peso
ADD CONSTRAINT Controllo_Peso CHECK (Peso>0);


--Gestisco l'eventualità che la chiave inserita sia nulla , autosmistamento.
CREATE OR REPLACE FUNCTION KeyNullaMerce()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.ID_Merce IS NULL THEN
        SELECT MAX(ID_Merce) + 1 INTO NEW.ID_Merce FROM Merce;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER GestioneMerce
BEFORE INSERT ON Merce
FOR EACH ROW
EXECUTE FUNCTION KeyNullaMerce();