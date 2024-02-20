--Creo la tabella Indirizzo
CREATE TABLE Indirizzo 
(
	Codice_Indirizzo INT PRIMARY KEY,
	Città VARCHAR(50) NOT NULL,
	CAP VARCHAR(50) NOT NULL,
	Via VARCHAR(50) NOT NULL,
	N_Civico INT NOT NULL
);

--Creazione dei vincolo
ALTER TABLE Indirizzo
--Controllo città 
ADD CONSTRAINT Controllo_città CHECK (Controllo_Città(città)),
--Controllo cap
ADD CONSTRAINT Controllo_CAP CHECK (Controllo_Numerico(CAP)),
--Controllo via
ADD CONSTRAINT Controllo_Via CHECK (Controllo_Città(Via));


--Gestisco l'eventualità che la chiave inserita sia nulla , autosmistamento.
CREATE OR REPLACE FUNCTION KeyNullaIndirizzo()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.Codice_Indirizzo IS NULL THEN
        SELECT MAX(Codice_Indirizzo) + 1 INTO NEW.Codice_Indirizzo FROM Indirizzo;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER GestioneIndirizzo
BEFORE INSERT ON Indirizzo
FOR EACH ROW
EXECUTE FUNCTION KeyNullaIndirizzo();