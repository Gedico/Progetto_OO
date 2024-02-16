--Creo la tabella Operatore 
CREATE TABLE Operatore
(
	ID_Operatore INT PRIMARY KEY,
	Username VARCHAR(50) NOT NULL,
	Nome VARCHAR(50) NOT NULL,
	Cognome VARCHAR(50) NOT NULL,
	Password VARCHAR(50) NOT NULL
);

--Inserisco il vincolo unique ad Username
ALTER TABLE Operatore
ADD CONSTRAINT UsernameUnico UNIQUE (Username),
--Controllo Nome
ADD CONSTRAINT Controllo_nome CHECK (Controllo_Nome(Nome)),
--Controllo Cognome
ADD CONSTRAINT Controllo_Cognome CHECK (Controllo_Cognome(Cognome)),
--Controllo Password
ADD CONSTRAINT Controllo_Password CHECK (LENGTH(password) BETWEEN 8 AND 25 AND password ~ '\d');


--Gestisco l'eventualità che la chiave inserita sia nulla , autosmistamento.
CREATE OR REPLACE FUNCTION KeyNullaOperatore()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.ID_Operatore IS NULL THEN
        SELECT MAX(ID_Operatore) + 1 INTO NEW.ID_Operatore FROM Operatore;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER GestioneOperatore
BEFORE INSERT ON Operatore
FOR EACH ROW
EXECUTE FUNCTION KeyNullaOperatore();


