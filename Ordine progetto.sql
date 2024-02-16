--Creazione del tipo enumerativo
CREATE TYPE Tipologia AS ENUM ('Standard', 'Premium', 'Ultimate');


--Creazione tabella Ordine
CREATE TABLE Ordine 
(
	ID_Ordine INT PRIMARY KEY,
	CF VARCHAR(50) NOT NULL,
	ID_Spedizione INT  NOT NULL,
	Codice_Indirizzo INT NOT NULL,
	DataOrdinato DATE NOT NULL,
	Elaborato BOOLEAN DEFAULT FALSE,
	Tipologia Tipologia NOT NULL
);

--Inserisco tutti i vincoli sulla tabella Ordine
ALTER TABLE Ordine

--Creazione foreign key su CF
ADD CONSTRAINT FK_CF FOREIGN KEY (CF)
	REFERENCES Cliente(CF) ON DELETE CASCADE,

--Creazione foreign key su ID_Spedizione
ADD CONSTRAINT FK_ID_Spedizione FOREIGN KEY (ID_Spedizione)
	REFERENCES Spedizione(ID_Spedizione) ON DELETE CASCADE,
	
--Creazione foreign key su Codice_Indirizzo
ADD CONSTRAINT FK_Codice_Indirizzo FOREIGN KEY (Codice_Indirizzo)
	REFERENCES Indirizzo(Codice_Indirizzo) ON DELETE CASCADE,
	
--Controllo sulla data 
ADD CONSTRAINT Verifica_Data_Inserita CHECK (DataOrdinato <= current_date);


--Gestisco l'eventualità che la chiave inserita sia nulla , autosmistamento.
CREATE OR REPLACE FUNCTION KeyNullaOrdine()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.ID_Ordine IS NULL THEN
        SELECT MAX(ID_Ordine) + 1 INTO NEW.ID_Ordine FROM Ordine;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER GestioneOrdine
BEFORE INSERT ON Ordine
FOR EACH ROW
EXECUTE FUNCTION KeyNullaOrdine();

