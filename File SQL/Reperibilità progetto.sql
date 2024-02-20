--Creo la tabella Reperibilità
CREATE TABLE Reperibilità
(
	Targa varchar(7) NOT NULL,
	Data DATE NOT NULL,
	Disponibilità BOOL DEFAULT FALSE,
	Maxtrasportabile int
);


--Gestisco i vincoli per Reperibiluità
ALTER TABLE Reperibilità
--Controllo sulla data 
ADD CONSTRAINT Controllo_Data CHECK (Data >= current_date),
--Creo la foreign key per Targa
ADD CONSTRAINT FK_Targa FOREIGN KEY (Targa)
    REFERENCES VeicolI(Targa) ON DELETE CASCADE,
--Controllo su maxtrasportabile
ADD CONSTRAINT Controllo_maxtrasportabile CHECK (maxtrasportabile >= 0);

--Trigger su maxTrasportabile
CREATE OR REPLACE FUNCTION ModificaDisponibilità()
RETURNS TRIGGER AS $$
BEGIN
	IF (NEW.maxtrasportabile=0)THEN
	SET NEW.Disponibilità = false;
	END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER DisponibilitàMaxTrasportabile
AFTER UPDATE ON reperibilità
FOR EACH ROW
EXECUTE FUNCTION ModificaDisponibilità();