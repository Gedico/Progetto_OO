CREATE OR REPLACE PROCEDURE crea_spedizione(
    IN id_ordine_IN INT,
    IN operatore_IN INT,
    IN targa_IN VARCHAR,
    IN data_IN DATE
)
LANGUAGE plpgsql    
AS $$
DECLARE
    pesotot INT;
BEGIN


IF (verifica_veicolo(id_ordine_IN,data_IN,targa_IN)and data_in >= current_date)THEN
    -- Calcola il peso totale dell'ordine
    SELECT SUM(s.quantità * m.peso) INTO pesotot
    FROM specifiche s
    JOIN merce m ON m.id_merce = s.id_merce
    WHERE s.id_ordine = id_ordine_IN;
	
	 RAISE NOTICE 'pesotot: %', pesotot;

    -- Inserisci i dati nella tabella spedizione
    INSERT INTO spedizione(id_spedizione, targa, id_operatore, peso_totale, data_spedizione, id_ordine)
    VALUES(NULL, targa_IN, operatore_IN, pesotot, data_IN, id_ordine_IN);
	
ELSE IF(data_in >= current_date)THEN
RAISE EXCEPTION 'La data deve essere ocorrente o futura';
ELSE RAISE EXCEPTION 'Il veicolo non può trasportare questo ordine';
END IF;
END IF;
END;
$$;

--trigger che modifica ordine 
CREATE OR REPLACE FUNCTION ModificaOrdine()
RETURNS TRIGGER AS $$
BEGIN
   UPDATE Ordine
   SET elaborato = true
   WHERE id_ordine = new.id_ordine;
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER GestioneOrdine
AFTER INSERT ON Spedizione
FOR EACH ROW
EXECUTE FUNCTION ModificaOrdine();


--trigger che modificail max trasportabileper data 
CREATE OR REPLACE FUNCTION ModificaMaxTrasp()
RETURNS TRIGGER AS $$
DECLARE 
pesotot int;
BEGIN

  SELECT SUM(s.quantità * m.peso) INTO pesotot
	   FROM specifiche s
	   JOIN merce m ON m.id_merce = s.id_merce
	   WHERE s.id_ordine = new.id_ordine;
	   
	   UPDATE reperibilità
	   SET maxtrasportabile = maxtrasportabile - pesotot
	   Where targa = NEW.targa and data = NEW.data_spedizione;
	 RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER GestioneMaxTrasp
AFTER INSERT ON Spedizione
FOR EACH ROW
EXECUTE FUNCTION ModificaMaxTrasp();


--trigger che modifica quantitativo in inventario 
CREATE OR REPLACE FUNCTION ModificaInventario()
RETURNS TRIGGER AS $$
BEGIN
  UPDATE inventario
  SET quantitativo = quantitativo - s.quantità
  FROM specifiche s
  WHERE s.id_ordine = NEW.id_ordine
    AND inventario.id_merce = s.id_merce
    AND inventario.id_magazzino = (SELECT id_magazzino FROM veicoli WHERE targa = NEW.targa);

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER GestioneInventario
AFTER INSERT ON Spedizione
FOR EACH ROW
EXECUTE FUNCTION ModificaInventario();




