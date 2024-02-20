	CREATE OR REPLACE FUNCTION calcolo_pesotot(id_ordine int)
	RETURNS INT AS $$

	DECLARE
	pesotot int;
	BEGIN
	SELECT SUM(s.quantità * m.peso) as pesotot INTO pesotot
	   FROM specifiche s
	   JOIN merce m ON m.id_merce = s.id_merce
	   WHERE s.id_ordine = codordine;

	   RETURN pesotot; 
	END;
	$$ LANGUAGE plpgsql;