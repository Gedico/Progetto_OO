--Creo tutte le funzioni che servono per i vincoli con le loro eccezioni

--Controllo di presenza solo numeri
CREATE OR REPLACE FUNCTION Controllo_Numerico(numero varchar) 
RETURNS BOOLEAN AS $$
BEGIN
  IF numero ~ '^[0-9]+$' THEN
    RETURN TRUE;
  ELSE
    RAISE EXCEPTION 'Il numeor non contiene solo numeri.';
    RETURN FALSE; -- Questo sarà raggiunto solo se l'eccezione non lanciata
  END IF;
END;
$$ LANGUAGE plpgsql;


-- Controllo di presenza solo lettere per il nome
CREATE OR REPLACE FUNCTION Controllo_Nome(nome text) 
RETURNS BOOLEAN AS $$
BEGIN
  IF nome ~ '^[a-zA-Zàèòùì]+$' THEN
    RETURN TRUE;
  ELSE
    RAISE EXCEPTION 'Il nome non contiene solo lettere:';
    RETURN FALSE;
  END IF;
END;
$$ LANGUAGE plpgsql;



--Controllo per il cognome
CREATE OR REPLACE FUNCTION Controllo_Cognome(cognome text) 
RETURNS BOOLEAN AS $$
BEGIN
  IF cognome ~ '^[a-zA-Z''\sàèòùì]+$' THEN
  	RETURN TRUE;
  ELSE
  	 RAISE EXCEPTION 'Il cognome contiene caratteri non consentiti';
	 RETURN FALSE;
  END IF;
END;
$$ LANGUAGE plpgsql;


--Controllo per il CF
CREATE OR REPLACE FUNCTION Controllo_Codfis(CF text) 
RETURNS BOOLEAN AS $$
BEGIN
	IF  CF ~ '^[a-zA-Z0-9]+$' THEN
	  RETURN TRUE;
	ELSE
	  RAISE EXCEPTION'Il codice fiscale contiene caratteri non ammessi';
	  RETURN FALSE;
	END IF;
END;
$$ LANGUAGE plpgsql;


--Controllo per città
CREATE OR REPLACE FUNCTION Controllo_Città(città varchar) 
RETURNS BOOLEAN AS $$
BEGIN
	IF  città ~ '^[a-zA-Z\sàèòùì\'']+$' THEN
	  RETURN TRUE;
	ELSE
	  RAISE EXCEPTION'La città contiene caratteri non ammessi';
	  RETURN FALSE;
	END IF;
END;
$$ LANGUAGE plpgsql;


--Controllo per Targa
CREATE OR REPLACE FUNCTION Controllo_Targa(Targa varchar) 
RETURNS BOOLEAN AS $$
BEGIN
	IF  Targa ~ '^[a-zA-Z0-9]+$' THEN
	  RETURN TRUE;
	ELSE
	  RAISE EXCEPTION'La targa contiene caratteri non ammessi';
	  RETURN FALSE;
	END IF;
END;
$$ LANGUAGE plpgsql;

-- Creazione di una funzione per verificare la quantità nel inventario
CREATE OR REPLACE FUNCTION Verifica_Quantita_Inventario(merce_id INT, quantita INT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN quantita < (SELECT max(Quantitativo) 
                    FROM Inventario 
                    WHERE ID_Merce = merce_id);
END;
$$ LANGUAGE plpgsql;


